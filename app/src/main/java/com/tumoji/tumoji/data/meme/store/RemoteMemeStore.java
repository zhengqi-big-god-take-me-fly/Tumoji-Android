package com.tumoji.tumoji.data.meme.store;

import android.media.MediaScannerConnection;

import com.tumoji.tumoji.TumojiApp;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.network.body.PostExpressionsReq;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.ImageApi;
import com.tumoji.tumoji.network.retrofit.MemeAPI;
import com.tumoji.tumoji.utils.ApplySchedulers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Author: perqin
 * Date  : 1/7/17
 */

public class RemoteMemeStore {
    private static final MemeAPI mMemeApi = APIFactory.getMemeAPIInstance();
    private static final ImageApi mImageApi = APIFactory.getImageApiInstance();

    public Observable<MemeModel> uploadNewMeme(String token, String title, File file) {
        // FIXME: change media type according to file extension
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return mImageApi.uploadImage(token, part).compose(ApplySchedulers.network())
                .flatMap(uploadImageRes -> mMemeApi.createMeme(token, new PostExpressionsReq(title, uploadImageRes.getUrl())).compose(ApplySchedulers.network()));
    }

    public Observable<List<MemeModel>> getMemes(int offset, int count, int order) {
        return mMemeApi.getMemes(offset, count, order == IMemeRepository.ORDER_MOST_POPULAR ? "likes.length DESC" : "createdAt DESC").compose(ApplySchedulers.network());
    }

    public Observable<List<MemeModel>> getMemesOfTag(int offset, int count, TagModel tagModel, int order) {
        return mMemeApi.getMemesOfTag(tagModel.getTagName(), offset, count, order == IMemeRepository.ORDER_MOST_POPULAR ? "likes.length DESC" : "createdAt DESC").compose(ApplySchedulers.network());
    }

    public Observable<MemeModel> getMemeById(String memeId) {
        return mMemeApi.getMemeById(memeId).compose(ApplySchedulers.network());
    }

    public Observable<Void> likeMeme(String token, String memeId, boolean like) {
        if (like) {
            return mMemeApi.likeMemeByIdAndToken(memeId, token).compose(ApplySchedulers.network())
                    .map(likeRelation -> null);
        } else {
            return mMemeApi.unlikeMemeByIdAndToken(memeId, token).compose(ApplySchedulers.network());
        }
    }

    public Observable<File> downloadMemeImage(String memeId, File destDir) {
        return getMemeById(memeId).flatMap(memeModel -> Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        File file = new File(destDir, getFilenameFromUrl(memeModel.getImageUrl()));
                        okhttp3.Response response = new OkHttpClient().newCall(new Request.Builder().url(memeModel.getImageUrl()).build()).execute();
                        InputStream inputStream = response.body().byteStream();
                        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                            byte[] buffer = new byte[4096];
                            int read;
                            while ((read = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, read);
                            }
                            fileOutputStream.flush();
                            MediaScannerConnection.scanFile(TumojiApp.myContext, new String[]{ file.getAbsolutePath() }, null, null);
                        }
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(file);
                            subscriber.onCompleted();
                        }
                    } catch (IOException e) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onError(e);
                        }
                    }
                }
            }
        }).compose(ApplySchedulers.network()));
    }

    private String getFilenameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
