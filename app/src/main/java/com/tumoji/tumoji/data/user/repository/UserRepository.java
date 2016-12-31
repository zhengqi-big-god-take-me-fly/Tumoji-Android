package com.tumoji.tumoji.data.user.repository;

import com.tumoji.tumoji.data.user.model.UserModel;
import com.tumoji.tumoji.data.user.store.LocalUserStore;
import com.tumoji.tumoji.data.user.store.RemoteUserStore;
import com.tumoji.tumoji.network.retrofit.NetworkScheduler;

import rx.Observable;

/**
 * Author: perqin
 * Date  : 12/31/16
 */

public class UserRepository implements IUserRepository {
    private static UserRepository sInstance;

    private LocalUserStore mLocal;
    private RemoteUserStore mRemote;

    public static UserRepository getInstance() {
        if (sInstance == null) {
            sInstance = new UserRepository();
        }
        return sInstance;
    }

    private UserRepository() {
        this.mLocal = new LocalUserStore();
        this.mRemote = new RemoteUserStore();
    }

    @Override
    public Observable<UserModel> getUser(String userId) {
        return mLocal.getUser(userId);
    }

    @Override
    public Observable<UserModel> findUser(String usernameOrEmail) {
        return mRemote.findUser(usernameOrEmail).compose(NetworkScheduler.applySchedulers());
    }

    @Override
    public Observable<UserModel> signUpNewUser(String username, String email, String password) {
        return mRemote.createUser(username, email, password).compose(NetworkScheduler.applySchedulers())
                .flatMap(userModel -> mLocal.saveOrUpdateUser(userModel));
    }

    @Override
    public Observable<UserModel> updateUser(String userId) {
        return mRemote.getUser(userId)
                .flatMap(userModel -> mLocal.saveOrUpdateUser(userModel));
    }
}
