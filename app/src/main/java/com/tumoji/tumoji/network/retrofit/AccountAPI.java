package com.tumoji.tumoji.network.retrofit;

import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.utils.AccountRole;
import com.tumoji.tumoji.utils.BooleanResponse;
import com.tumoji.tumoji.utils.ErrorType;
import com.tumoji.tumoji.utils.IntegerResponse;
import com.tumoji.tumoji.utils.Token;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by souler on 16-12-15.
 */
public interface AccountAPI {

    @GET("/users")
    Observable<List<AccountModel>> getAllUsers();

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
    @PUT("/users")
    Observable<AccountModel> updateUserRaw(@Body RequestBody route);

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
    @POST("/users")
    Observable<AccountModel> createUser(@Body RequestBody route);

    @GET("/users/{id}")
    Observable<AccountModel> getUserById(@Path("id") String id);


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
    @PUT("/users/{id}")
    Observable<AccountModel> updateUserById(@Path("id") String id , @Body RequestBody route , @Field("access_token") String token);


    @DELETE("/users/{id}")
    Observable<ErrorType> deleteUserById(@Path("id") String id);

    @GET("/users/{id}/accessTokens")
    Observable<Token> getAccessTokenByUserId(@Path("id") String id);

    @DELETE("/users/{id}/accessTokens")
    Observable<ErrorType> removeAllAccessTokensByUserId(@Path("id") String id, @Field("access_token") String token);

    @GET("/users/{id}/accessTokens/count")
    Observable<IntegerResponse> checkAccessTokenCountByUserId(@Path("id") String id);

    @GET("/users/{id}/exists")
    Observable<BooleanResponse> checkUserExistenceByUserId(@Path("id") String id);

    @GET("/users/{id}/expressions")
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
    @POST("/users/{id}/expressions")
    Observable<MemeModel> addNewExpressionByUser(@Path("id") String id , @Body RequestBody route , @Field("access_token") String token);

    @DELETE("/users/{id}/expressions")
    Observable<ErrorType> removeAllExpressionsByUserId(@Path("id") String id);

    @GET("/users/{id}/expressions/count")
    Observable<IntegerResponse> getUserExpressionCountById(@Path("id") String id);

    @GET("/users/{id}/likes")
    Observable<List<MemeModel>> getAllLikedMemesByUserId(@Path("id") String id , @Field("access_token") String token);

    @GET("/users/{id}/likes/count")
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
    @POST("/users/{id}/replace")
    Observable<AccountModel> replaceUserById(@Path("id") String id , @Body RequestBody route , @Field("access_token") String token);

    @GET("/users/{id}/roles")
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
    @POST("/users/login")
    Observable<Token> requestLogin(@Body RequestBody route);


    @POST("/users/logout")
    Observable<ErrorType> requstLogout(@Field("access_token") String token);


}
