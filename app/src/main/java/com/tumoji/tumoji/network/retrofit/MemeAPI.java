package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.data.auth.model.AuthModel;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.network.body.PostExpressionsReq;
import com.tumoji.tumoji.utils.BooleanResponse;
import com.tumoji.tumoji.utils.ErrorType;
import com.tumoji.tumoji.utils.IntegerResponse;
import com.tumoji.tumoji.utils.LikeRelation;

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
public interface MemeAPI {
    @GET("/expressions")
    Observable<List<MemeModel>> getAllMemes();

    @GET("expressions")
    Observable<List<MemeModel>> getMemes(@Query("filter[offset]") int offset, @Query("filter[limit]") int limit, @Query("filter[order]") String order);

    @GET("tags/{tag_name}/expressions")
    Observable<List<MemeModel>> getMemesOfTag(@Path("tag_name") String tagName, @Query("filter[offset]") int offset, @Query("filter[limit]") int limit, @Query("filter[order]") String order);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(memeModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @POST("/expressions")
    Observable<MemeModel> createMeme(@Body RequestBody body);

    @POST("expressions")
    Observable<MemeModel> createMeme(@Query("access_token") String token, @Body PostExpressionsReq body);

    @GET("expressions/{id}")
    Observable<MemeModel> getMemeById(@Path("id") String id);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(memeModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param id
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @PUT("/expressions/{id}")
    Observable<MemeModel> updateMeme(@Path("id") String id , @Body RequestBody route , @Field("access_token") String token);


    @DELETE("/expressions/{id}")
    Observable<IntegerResponse> removeMemeById(@Path("id") String id , @Field("access_token") String token);

    @GET("/expressions/{id}/author")
    Observable<AuthModel> getMemeAuthorByMemeId(@Path("id") String id);

    @GET("/expressions/{id}/exists")
    Observable<BooleanResponse> checkMemeExistenceById(@Path("id") String id);


    /**
     * Simple post without Json
     * @param id
     * @param token
     * @return
     */
    @POST("/expressions/{id}/like")
    Observable<LikeRelation> likeMemeByIdAndToken(@Path("id") String id , @Field("access_token") String token);

    @DELETE("/expressions/{id}/like")
    Observable<ErrorType> unlikeMemeByIdAndToken(@Path("id") String id , @Field("access_token") String token);

    @GET("/expressions/{id}/likes")
    Observable<List<AuthModel>> getLikersOfMemeById(@Path("id") String id);

    @GET("/expressions/{id}/likes/count")
    Observable<IntegerResponse> getLikersCountOfMemeById(@Path("id") String id);

    @GET("/expressions/{id}/tags")
    Observable<List<TagModel>> getTagsOfMemeById(@Path("id") String id);

    @GET("/expressions/{id}/tags/count")
    Observable<IntegerResponse> getTagsCountOfMemeById(@Path("id") String id);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(tagModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param id
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @POST("/expressions/{id}/tags")
    Observable<TagModel> createNewTagOnMemeById(@Path("id") String id , @Body RequestBody route ,@Field("access_token") String token);


    @GET("/expressions/count")
    Observable<IntegerResponse> getCountOfMemeById(@Path("id") String id);



}
