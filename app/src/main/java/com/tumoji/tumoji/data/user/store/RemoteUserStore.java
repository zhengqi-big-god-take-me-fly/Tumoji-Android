package com.tumoji.tumoji.data.user.store;

import com.google.gson.Gson;
import com.tumoji.tumoji.data.user.model.SignUpUserModel;
import com.tumoji.tumoji.data.user.model.UserModel;
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

public class RemoteUserStore {
    private static final AccountAPI mAccountApi = APIFactory.getAccountAPIInstance();

    public Observable<UserModel> findUser(String usernameOrEmail) {
        return mAccountApi.findAccount(usernameOrEmail, usernameOrEmail).compose(ApplySchedulers.network()).map(userModels -> {
            if (userModels.size() == 0) {
                throw new RuntimeException("Cannot find this user");
            }
            return userModels.get(0);
        });
    }

    public Observable<UserModel> getUser(String id) {
        return mAccountApi.getUserById(id).compose(ApplySchedulers.network());
    }

    public Observable<UserModel> createUser(String username, String email, String password) {
        SignUpUserModel model = new SignUpUserModel("", username, email, "", password);
        return mAccountApi.createUser(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(model))).compose(ApplySchedulers.network());
    }
}
