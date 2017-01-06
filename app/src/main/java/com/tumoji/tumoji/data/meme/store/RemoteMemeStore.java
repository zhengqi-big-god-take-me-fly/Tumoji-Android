package com.tumoji.tumoji.data.meme.store;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.network.body.PostExpressionsReq;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.ImageApi;
import com.tumoji.tumoji.network.retrofit.MemeAPI;
import com.tumoji.tumoji.utils.ApplySchedulers;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

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
}
