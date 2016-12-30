package com.tumoji.tumoji.data.user.store;

import com.tumoji.tumoji.data.user.model.UserModel;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.AccountAPI;

import rx.Observable;

/**
 * Author: perqin
 * Date  : 12/31/16
 */

public class RemoteUserStore {
    public static final AccountAPI mAccountApi = APIFactory.getAccountAPIInstance();

    public Observable<UserModel> findUser(String usernameOrEmail) {
        return mAccountApi.findAccount(usernameOrEmail, usernameOrEmail).map(userModels -> {
            if (userModels.size() == 0) {
                throw new RuntimeException("Cannot find this user");
            }
            return userModels.get(0);
        });
    }

    public Observable<UserModel> getUser(String id) {
        return mAccountApi.getUserById(id);
    }

    public Observable<UserModel> createUser(String username, String email, String password) {
        return null;
    }
}
