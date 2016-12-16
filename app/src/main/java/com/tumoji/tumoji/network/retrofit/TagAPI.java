package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.utils.BooleanResponse;
import com.tumoji.tumoji.utils.IntegerResponse;

import java.util.List;


import retrofit2.adapter.rxjava.Result;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HEAD;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

import retrofit2.http.GET;

/**
 * Created by souler on 16-12-14.
 */
public interface TagAPI {

    @GET("/tags")
    Observable<List<TagModel>> getAllTags();

    @GET("/tags/{id}")
    Observable<TagModel> getTagById(@Path("id") String id);

    @FormUrlEncoded
    @POST("/tags")
    Observable<TagModel> createTag(@Field("name") String name , @Field("description") String description);

    @FormUrlEncoded
    @PUT("/tags/{id}")
    Observable<TagModel> updateTag(@Path("id") String id , @Field("name") String name , @Field("description") String description);

    @DELETE("/tags/{id}")
    Observable<TagModel> removeTag(@Path("id") String id);

    @GET("/tags/{id}/exists")
    Observable<BooleanResponse> tagExists(@Path("id") String id);

    @GET("/tags/{id}/expressions")
    Observable<List<MemeModel>> getMemeByTag(@Path("id") String id);

    @GET("/tags/{id}/expressions/count")
    Observable<IntegerResponse> getMemeCountByTag(@Path("id") String id);

    @GET("/tags/count")
    Observable<IntegerResponse> getTagCount();


}
