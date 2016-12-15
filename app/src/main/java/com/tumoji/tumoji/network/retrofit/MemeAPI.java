package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.data.meme.model.MemeModel;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by souler on 16-12-14.
 */
public interface MemeAPI {
    @GET("/expressions")
    Observable<List<MemeModel>> getAllMemes();

    @FormUrlEncoded
    @POST("/expressions")
    Observable<MemeModel> createMeme(@Field("id") String id , @Field("title") String title , @Field("authodId") String authorId , @Field("image") String imageUrl);

    @GET("expressions/{id}")
    Observable<MemeModel> getMemeById(@Path("id") String id);

    @FormUrlEncoded
    @PUT("expressions/{id}")
    Observable<MemeModel> updateMeme(@Path("id") @Field("id") String id , @Field("title") String title , @Field("authodId") String authorId , @Field("image") String imageUrl);

    @DELETE("expressions/{id}")
    Observable<MemeModel> removeMemeById(@Path("id") String id);
}
