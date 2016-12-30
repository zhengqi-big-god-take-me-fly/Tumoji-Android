package com.tumoji.tumoji.data.user.store;

import com.tumoji.tumoji.data.user.model.UserModel;
import com.tumoji.tumoji.storage.sqlite.AccountDatabase;

import rx.Observable;
import rx.Subscriber;

/**
 * Author: perqin
 * Date  : 12/31/16
 */

public class LocalUserStore {
    private final AccountDatabase mDb;

    public LocalUserStore() {
        mDb = AccountDatabase.getInstance();
    }

    public Observable<UserModel> saveOrUpdateUser(UserModel userModel) {
        return null;
    }

    public Observable<UserModel> getUser(String userId) {
        return Observable.create(new Observable.OnSubscribe<UserModel>() {
            @Override
            public void call(Subscriber<? super UserModel> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    UserModel userModel = mDb.getUserByUserId(userId);
                    subscriber.onNext(userModel);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
