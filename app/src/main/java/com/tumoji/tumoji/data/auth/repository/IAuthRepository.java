package com.tumoji.tumoji.data.auth.repository;

import com.tumoji.tumoji.data.auth.model.AuthModel;

import rx.Observable;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public interface IAuthRepository {
    /**
     * Check whether there is a signed in account locally.
     * Typically, you implement this method by checking the availability of access token
     * @return Whether there is a signed in account
     */
    boolean isSignedIn();

    /**
     * Get the account of current signed in user
     * The returned model is the locally stored data, not the latest data from API server
     * @return The model of current signed in user, or null if no signed in user.
     */
    AuthModel getLocalAuth();

    /**
     * Sign in with username or email
     * @param usernameOrEmail Username or email
     * @param isUsername Whether the usernameOrEmail field is username
     * @param password Password
     * @return Observable which emits signed in account data
     */
    Observable<AuthModel> signIn(String usernameOrEmail, boolean isUsername, String password);

    /**
     * Sign out currently signed in user
     * @return RxJava observable object
     */
    Observable<Void> signOut();
}
