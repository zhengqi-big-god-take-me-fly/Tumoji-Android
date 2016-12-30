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

//    /**
//     * Get latest account profile AND update local profile data
//     * This method should firstly retrieve latest profile data from server, then update local data
//     * as well as data in memory, and after that emit result.
//     * @return The observable which emits a AuthModel
//     */
//    Observable<AuthModel> updateSignedInAccount();
//
//    /**
//     * Find a account with username or email
//     * @param usernameOrEmail The username or email of the account
//     * @return Observable which emits a FindAccountResultModel indicating whether there is such an
//     * account
//     */
//    Observable<FindAccountResultModel> findAccount(String usernameOrEmail);
//
//    /**
//     * Sign up for user
//     * @param username Username
//     * @param email Email address
//     * @param password Password
//     * @return Observable which emits the signed up AuthModel
//     */
//    Observable<AuthModel> signUp(String username, String email, String password);

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

//    /**
//     * @deprecated Use {@link #isSignedIn()} instead.
//     * Check whether there is a signed in account. Use this instead of {@link #getLocalAuth()}
//     * for quick check
//     * @return Whether there is a signed in account
//     */
//    boolean hasSignedInAccount();
//
//    /**
//     * @deprecated User {@link #updateSignedInAccount()} instead
//     * Get latest account profile and update local profile data
//     * @param listener Callback
//     */
//    void updateSignedInAccount(OnGetResultListener<AuthModel> listener);
//
//    /**
//     * @deprecated Consider using {@link #signOut()} instead.
//     * Sign out currently signed in user and give operation result
//     * @param listener Callback
//     */
//    void signOut(OnGetNaiveResultListener listener);
//
//    /**
//     * @deprecated User {@link #signUp(String, String, String)} instead.
//     * Sign up for user
//     * @param username Username
//     * @param email Email address
//     * @param password Password
//     * @param listener Callback
//     */
//    void signUp(String username, String email, String password, OnGetNaiveResultListener listener);
//
//    /**
//     * @deprecated Use {@link #signIn(String, boolean, String)} instead.
//     * Sign in with username or email
//     * @param usernameOrEmail Username or email
//     * @param isUsername Whether the usernameOrEmail field is username
//     * @param password Password
//     * @param listener Callback
//     */
//    void signIn(String usernameOrEmail, boolean isUsername, String password, OnGetNaiveResultListener listener);
}
