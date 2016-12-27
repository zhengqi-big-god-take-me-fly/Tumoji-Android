package com.tumoji.tumoji.data.account.repository;

import com.tumoji.tumoji.common.OnGetNaiveResultListener;
import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.account.model.AccountModel;

import rx.Observable;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public interface IAccountRepository {

    /**
     * Get the account of current signed in user
     * @return The model of current signed in user, or null if no signed in user.
     */
    AccountModel getSignedInAccount();

    /**
     * Check whether there is a signed in account. Use this instead of {@link #getSignedInAccount()}
     * for quick check
     * @return Whether there is a signed in account
     */
    boolean hasSignedInAccount();

    /**
     * Get latest account profile and update local profile data
     * @param listener Callback
     */
    void updateSignedInAccount(OnGetResultListener<AccountModel> listener);

    /**
     * @deprecated Consider using {@link #signOut()} instead.
     * Sign out currently signed in user and give operation result
     * @param listener Callback
     */
    void signOut(OnGetNaiveResultListener listener);

    /**
     * Sign out currently signed in user
     * @return RxJava observable object
     */
    Observable<Void> signOut();

    /**
     * Sign up for user
     * @param username Username
     * @param email Email address
     * @param password Password
     * @param listener Callback
     */
    void signUp(String username, String email, String password, OnGetNaiveResultListener listener);

    /**
     * @deprecated Use {@link #signIn(String, boolean, String)} instead.
     * Sign in with username or email
     * @param usernameOrEmail Username or email
     * @param isUsername Whether the usernameOrEmail field is username
     * @param password Password
     * @param listener Callback
     */
    void signIn(String usernameOrEmail, boolean isUsername, String password, OnGetNaiveResultListener listener);

    Observable<Void> signIn(String usernameOrEmail, boolean isUsername, String password);

//    Observable<FindAccountResultModel> findAccount(String usernameOrEmail);
}
