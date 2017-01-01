package com.tumoji.tumoji.data.auth.store;

import android.content.Context;
import android.content.SharedPreferences;

import com.tumoji.tumoji.data.auth.model.AuthModel;
import com.tumoji.tumoji.storage.preferences.SharedPreferencesFactory;
import com.tumoji.tumoji.utils.ApplySchedulers;

import rx.Observable;
import rx.Subscriber;

/**
 * Author: perqin
 * Date  : 12/31/16
 */

public class LocalAuthStore {
    private final SharedPreferences mSharedPreferences;
    private AuthModel mLocalAuth;

    public LocalAuthStore(Context context) {
        mSharedPreferences = SharedPreferencesFactory.getAppDefaultSharedPreferences(context);
        if (!mSharedPreferences.getString(SharedPreferencesFactory.PK_SIGNED_IN_USER_ID, "").equals("")) {
            mLocalAuth = new AuthModel()
                    .withUserId(mSharedPreferences.getString(SharedPreferencesFactory.PK_SIGNED_IN_USER_ID, ""))
                    .withAccessToken(mSharedPreferences.getString(SharedPreferencesFactory.PK_ACCESS_TOKEN, ""));
        } else {
            mLocalAuth = null;
        }
    }

    public AuthModel getLocalAuth() {
        return mLocalAuth;
    }

    public String getAccessToken() {
        return mLocalAuth.getAccessToken();
    }

    public String getUserId() {
        return mLocalAuth.getUserId();
    }

    public Observable<Void> removeAuth() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    removeAuthSync();
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                }
            }
        }).compose(ApplySchedulers.storage());
    }

    public void removeAuthSync() {
        mSharedPreferences.edit()
                .remove(SharedPreferencesFactory.PK_SIGNED_IN_USER_ID)
                .remove(SharedPreferencesFactory.PK_ACCESS_TOKEN)
                .apply();
        mLocalAuth = null;
    }

    public boolean available() {
        return mLocalAuth != null;
    }

    public Observable<AuthModel> saveAuth(AuthModel authModel) {
        return Observable.create(new Observable.OnSubscribe<AuthModel>() {
            @Override
            public void call(Subscriber<? super AuthModel> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(saveAuthSync(authModel));
                    subscriber.onCompleted();
                }
            }
        }).compose(ApplySchedulers.storage());
    }

    public AuthModel saveAuthSync(AuthModel authModel) {
        mSharedPreferences.edit()
                .putString(SharedPreferencesFactory.PK_SIGNED_IN_USER_ID, authModel.getUserId())
                .putString(SharedPreferencesFactory.PK_ACCESS_TOKEN, authModel.getAccessToken())
                .apply();
        mLocalAuth = new AuthModel().withUserId(authModel.getUserId()).withAccessToken(authModel.getAccessToken());
        return mLocalAuth;
    }
}
