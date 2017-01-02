package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.network.body.UploadImageRes;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: perqin
 * Date  : 1/2/17
 */

public interface ImageApi {
    @Multipart
    @POST("images")
    Observable<UploadImageRes> uploadImage(@Query("access_token") String token, @Part MultipartBody.Part file);
}
