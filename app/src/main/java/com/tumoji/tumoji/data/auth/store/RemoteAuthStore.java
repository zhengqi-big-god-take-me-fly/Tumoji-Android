package com.tumoji.tumoji.data.auth.store;

import com.google.gson.Gson;
import com.tumoji.tumoji.data.auth.model.AccountLoginModel;
import com.tumoji.tumoji.data.auth.model.AuthModel;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.AccountAPI;
import com.tumoji.tumoji.utils.ApplySchedulers;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Author: perqin
 * Date  : 12/31/16
 */

public class RemoteAuthStore {
    // Well , I do think that Retrofit should used in presenter level
    private static final AccountAPI mAccountApi = APIFactory.getAccountAPIInstance();

    public Observable<AuthModel> makeAuth(String usernameOrEmail, boolean isUsername, String password) {
        AccountLoginModel model = new AccountLoginModel();
        if (isUsername) {
            model.setUsername(usernameOrEmail);
            model.setEmail("");
        } else {
            model.setUsername("");
            model.setEmail(usernameOrEmail);
        }
        model.setPassword(password);
        return mAccountApi.requestLogin(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(model)))
                .compose(ApplySchedulers.network())
                .map(token -> new AuthModel(token.getUserId(), token.getId()));
    }

    public Observable<Void> removeAuth(String token) {
        return mAccountApi.requestLogout(token).compose(ApplySchedulers.network());
    }
}
