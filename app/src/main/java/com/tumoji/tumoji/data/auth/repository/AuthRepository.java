package com.tumoji.tumoji.data.auth.repository;

import android.content.Context;

import com.tumoji.tumoji.data.auth.model.AuthModel;
import com.tumoji.tumoji.data.auth.store.LocalAuthStore;
import com.tumoji.tumoji.data.auth.store.RemoteAuthStore;

import rx.Observable;

/**
 * Author: souler
 * Date  : 16-12-19
 *
 * NOTE: This repository only take charge of account authentication.
 */
public class AuthRepository implements IAuthRepository {
    private LocalAuthStore mLocal;
    private RemoteAuthStore mRemote;

    // Singleton
    private static AuthRepository sInstance;

    private AuthRepository(Context context) {
        mLocal = new LocalAuthStore(context);
        mRemote = new RemoteAuthStore();
    }

    public static AuthRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AuthRepository(context);
        }
        return sInstance;
    }

    /**
     * Get the account of current signed in user
     *
     * @return The model of current signed in user, or null if no signed in user.
     */
    @Override
    public AuthModel getLocalAuth() {
        return mLocal.getLocalAuth();
    }

    @Override
    public boolean isSignedIn() {
        return mLocal.available();
    }

    @Override
    public Observable<Void> signOut() {
//        return Observable.concat(mRemote.removeAuth(mLocal.getAccessToken()), mLocal.removeAuth());
        // NOTE: To force signing out, remove local auth before removing remote auth
        String token = mLocal.getAccessToken();
        return Observable.concat(mLocal.removeAuth(), mRemote.removeAuth(token));
    }

    @Override
    public Observable<AuthModel> signIn(String usernameOrEmail, boolean isUsername, String password) {
        return mRemote.makeAuth(usernameOrEmail, isUsername, password)
                .flatMap(authModel -> mLocal.saveAuth(authModel));
    }
}
