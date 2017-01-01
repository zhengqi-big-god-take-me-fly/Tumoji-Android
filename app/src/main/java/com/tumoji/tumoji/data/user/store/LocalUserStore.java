package com.tumoji.tumoji.data.user.store;

import com.tumoji.tumoji.data.user.model.UserModel;
import com.tumoji.tumoji.storage.sqlite.AccountDatabase;
import com.tumoji.tumoji.utils.ApplySchedulers;

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
        return Observable.create(new Observable.OnSubscribe<UserModel>() {
            @Override
            public void call(Subscriber<? super UserModel> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(saveOrUpdateUserSync(userModel));
                    subscriber.onCompleted();
                }
            }
        }).compose(ApplySchedulers.storage());
    }

    public UserModel saveOrUpdateUserSync(UserModel userModel) {
        return mDb.insertOrReplaceUser(userModel);
    }

    public Observable<UserModel> getUserById(String userId) {
        return Observable.create(new Observable.OnSubscribe<UserModel>() {
            @Override
            public void call(Subscriber<? super UserModel> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(getUserByIdSync(userId));
                    subscriber.onCompleted();
                }
            }
        }).compose(ApplySchedulers.storage());
    }

    public UserModel getUserByIdSync(String userId) {
        return mDb.getUserByUserId(userId);
    }
}
