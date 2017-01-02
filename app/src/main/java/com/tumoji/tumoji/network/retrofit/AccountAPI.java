package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.data.auth.model.AuthModel;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.user.model.UserModel;
import com.tumoji.tumoji.network.body.PutUserAvatarReq;
import com.tumoji.tumoji.utils.AccountRole;
import com.tumoji.tumoji.utils.BooleanResponse;
import com.tumoji.tumoji.utils.ErrorType;
import com.tumoji.tumoji.utils.IntegerResponse;
import com.tumoji.tumoji.utils.Token;

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
 * Created by souler on 16-12-15.
 */
public interface AccountAPI {

    @GET("users")
    Observable<List<AuthModel>> getAllUsers();

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(accountModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @PUT("users")
    Observable<UserModel> updateUserRaw(@Body RequestBody route);

    /**
     * FIXME: UserModel doesn't have password field
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(accountModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @POST("users")
    Observable<UserModel> createUser(@Body RequestBody route);

    @GET("users/{id}")
    Observable<UserModel> getUserById(@Path("id") String id);

    /**
     * Get users with filter
     * Query format: ?filter[where][or][0][username]=USERNAME&filter[where][or][1][email]=EMAIL
     * @param username Username to search
     * @param email Email to search
     * @return The observable which emits a list of account models.
     */
    @GET("users")
    Observable<List<UserModel>> findAccount(@Query("filter[where][or][0][username]") String username, @Query("filter[where][or][1][email]") String email);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(accountModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @PUT("users/{id}")
    Observable<UserModel> updateUserById(@Path("id") String id , @Body RequestBody route , @Field("access_token") String token);


    @DELETE("users/{id}")
    Observable<ErrorType> deleteUserById(@Path("id") String id);

    @GET("users/{id}/accessTokens")
    Observable<Token> getAccessTokenByUserId(@Path("id") String id);

    @DELETE("users/{id}/accessTokens")
    Observable<ErrorType> removeAllAccessTokensByUserId(@Path("id") String id, @Field("access_token") String token);

    @GET("users/{id}/accessTokens/count")
    Observable<IntegerResponse> checkAccessTokenCountByUserId(@Path("id") String id);

    @GET("users/{id}/exists")
    Observable<BooleanResponse> checkUserExistenceByUserId(@Path("id") String id);

    @GET("users/{id}/expressions")
    Observable<List<MemeModel>> getExpressionsPostedByUserId(@Path("id") String id);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(memeModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @POST("users/{id}/expressions")
    Observable<MemeModel> addNewExpressionByUser(@Path("id") String id , @Body RequestBody route , @Field("access_token") String token);

    @DELETE("users/{id}/expressions")
    Observable<ErrorType> removeAllExpressionsByUserId(@Path("id") String id);

    @GET("users/{id}/expressions/count")
    Observable<IntegerResponse> getUserExpressionCountById(@Path("id") String id);

    @GET("users/{id}/likes")
    Observable<List<MemeModel>> getAllLikedMemesByUserId(@Path("id") String id , @Field("access_token") String token);

    @GET("users/{id}/likes/count")
    Observable<IntegerResponse> getAllLikedMemesCountByUserId(@Path("id") String id , @Field("access_token") String token);

    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(accountModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @POST("users/{id}/replace")
    Observable<UserModel> replaceUserById(@Path("id") String id , @Body RequestBody route , @Field("access_token") String token);

    @GET("users/{id}/roles")
    Observable<AccountRole> getUserRoleById(@Path("id") String id , @Field("access_token") String token);


    /**
     * Need to PUT JSON Here
     * Need to initialized by these steps:
     * -> Gson gson = new Gson();
     * -> String route = gson.toJson(accountLoginModel);
     * -> RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
     * Then we can put body when calling this methods
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json" , "Accept: application/json"})
    @POST("users/login")
    Observable<Token> requestLogin(@Body RequestBody route);


    @POST("users/logout")
    Observable<Void> requestLogout(@Query("access_token") String token);

    @PUT("users/{id}")
    Observable<UserModel> changeUserAvatar(@Path("id") String userId, @Query("access_token") String token, @Body PutUserAvatarReq req);
}
