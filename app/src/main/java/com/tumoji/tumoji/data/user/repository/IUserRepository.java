package com.tumoji.tumoji.data.user.repository;

import com.tumoji.tumoji.data.user.model.UserModel;

import java.io.File;

import rx.Observable;

/**
 * Author: perqin
 * Date  : 12/31/16
 */

public interface IUserRepository {
    Observable<UserModel> getUser(String userId);

    Observable<UserModel> findUser(String usernameOrEmail);

    Observable<UserModel> signUpNewUser(String username, String email, String password);

    Observable<UserModel> updateUser(String userId);

    Observable<UserModel> changeUserAvatar(String token, String userId, File file);
}
