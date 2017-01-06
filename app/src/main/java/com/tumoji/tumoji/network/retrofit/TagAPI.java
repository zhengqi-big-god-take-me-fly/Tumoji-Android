package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.utils.BooleanResponse;
import com.tumoji.tumoji.utils.IntegerResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by souler on 16-12-14.
 */
public interface TagAPI {
    @PUT("expressions/{meme_id}/tags/rel/{tag_name}")
    Observable<TagModel> relTagToMeme(@Path("meme_id") String memeId, @Path("tag_name") String tagName, @Query("access_token") String token);

    @GET("tags")
    Observable<List<TagModel>> getAllTags();

    @GET("/tags/{name}")
    Observable<TagModel> getTagByName(@Path("name") String name);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(tagModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @POST("/tags")
    Observable<TagModel> createTag(@Body RequestBody route , @Field("access_token") String token);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(tagModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param name
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @PUT("/tags/{name}")
    Observable<TagModel> updateTag(@Path("name") String name , @Body RequestBody route , @Field("accessToken") String token);


    @DELETE("/tags/{id}")
    Observable<TagModel> removeTag(@Path("id") String id , @Field("access_token") String token);

    @GET("/tags/{id}/exists")
    Observable<BooleanResponse> tagExists(@Path("id") String id);

    @GET("/tags/{id}/expressions")
    Observable<List<MemeModel>> getMemeByTag(@Path("id") String id);

    @GET("/tags/{id}/expressions/count")
    Observable<IntegerResponse> getMemeCountByTag(@Path("id") String id);

    @GET("/tags/count")
    Observable<IntegerResponse> getTagCount();

    @GET("expressions/{id}/tags")
    Observable<List<TagModel>> getTagsOfMeme(@Path("id") String memeId);
}
