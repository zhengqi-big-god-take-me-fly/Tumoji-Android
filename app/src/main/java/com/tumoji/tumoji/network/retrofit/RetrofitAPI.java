package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.TumojiApp;
import com.tumoji.tumoji.utils.StateUtils;

import java.io.File;
import java.net.CacheRequest;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by souler on 16-12-14.
 */
public class RetrofitAPI {
    public MemeAPI memeService;
    public TagAPI tagService;
    public AccountAPI accountService;

    public static final String MEME_BASE_URL = "http://place.holder.com/" +
            "";
    public static final String TAG_BASE_URL = "";

    public static final String ACCOUNT_BASE_URL = "";

    /**
     * Getters
     * @return
     */
    public TagAPI getTagService() {
        return tagService;
    }

    public MemeAPI getMemeService() {
        return memeService;
    }

    public AccountAPI getAccountService() {
        return accountService;
    }

    RetrofitAPI() {
        File httpCacheDirectory = new File(TumojiApp.myContext.getCacheDir() , "responses");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory , cacheSize);

        /**
         * Interceptor
         */
        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
            /**
             * Build Up cacheControl
             */
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365 , TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            /**
             * Request
             */
            Request request = chain.request();
            if (!StateUtils.isNetworkAvailable(TumojiApp.myContext)) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            /**
             * Response
             */
            Response originalResponse = chain.proceed(request);
            if (StateUtils.isNetworkAvailable(TumojiApp.myContext)) {
                int maxAge = 0; // Readfrom cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control" , "public , max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4 weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control" , "public , only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };


        /**
         * Init Okhttp Client
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .build();

        Retrofit memeRetro = new Retrofit.Builder()
                .baseUrl(MEME_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Retrofit tagRetro = new Retrofit.Builder()
                .baseUrl(TAG_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Retrofit accountRetro = new Retrofit.Builder()
                .baseUrl(ACCOUNT_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        memeService = memeRetro.create(MemeAPI.class);
        tagService = tagRetro.create(TagAPI.class);
        accountService = accountRetro.create(AccountAPI.class);

    }
}
