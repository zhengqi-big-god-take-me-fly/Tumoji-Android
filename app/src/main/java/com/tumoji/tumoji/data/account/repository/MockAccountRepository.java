package com.tumoji.tumoji.data.account.repository;

import android.content.Context;
import android.os.Handler;

import com.tumoji.tumoji.common.OnGetNaiveResultListener;
import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.data.account.model.FindAccountResultModel;

import rx.Observable;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class MockAccountRepository implements IAccountRepository {
    private static MockAccountRepository sInstance;

    private AccountModel mAccountModel;
    // Delegate from real repo, to integrate functionality gradually.
    private IAccountRepository mDelegate;

    public static MockAccountRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MockAccountRepository(context);
        }
        return sInstance;
    }

    private MockAccountRepository(Context context) {
        mDelegate = AccountRepository.getInstance(context);
        mAccountModel = null;
    }

    @Override
    public AccountModel getSignedInAccount() {
        return mAccountModel;
    }

    @Override
    public boolean hasSignedInAccount() {
        return mAccountModel != null;
    }

    @Override
    public boolean isSignedIn() {
        return mDelegate.isSignedIn();
    }

    @Override
    public void updateSignedInAccount(OnGetResultListener<AccountModel> listener) {
//        new Handler().postDelayed(() -> {
//            mAccountModel.setUsername("Perqin Ultimate");
//            mAccountModel.setEmail("another@email.com");
//            listener.onSuccess(mAccountModel);
//        }, 500);
    }

    @Override
    public Observable<AccountModel> updateSignedInAccount() {
        return mDelegate.updateSignedInAccount();
    }

    @Override
    public void signOut(OnGetNaiveResultListener listener) {
        new Handler().postDelayed(() -> {
            mAccountModel = null;
            listener.onSuccess();
        }, 500);
    }

    @Override
    public Observable<Void> signOut() {
        return mDelegate.signOut();
    }

    @Override
    public void signUp(String username, String email, String password, OnGetNaiveResultListener listener) {
        new Handler().postDelayed(() -> {
            mAccountModel = new AccountModel()
                    .withUsername(username)
                    .withEmail(email)
                    .withAvatarUrl("http://t.perqin.com/img/avatar.png")
                    .withUserId("1");
            listener.onSuccess();
        }, 500);
    }

    @Override
    public void signIn(String usernameOrEmail, boolean isUsername, String password, OnGetNaiveResultListener listener) {
        throw new RuntimeException("Method deprecated");
    }

    @Override
    public Observable<AccountModel> signIn(String usernameOrEmail, boolean isUsername, String password) {
        return mDelegate.signIn(usernameOrEmail, isUsername, password);
    }

    @Override
    public Observable<FindAccountResultModel> findAccount(String usernameOrEmail) {
        return mDelegate.findAccount(usernameOrEmail);
    }

    @Override
    public Observable<AccountModel> signUp(String username, String email, String password) {
        return mDelegate.signUp(username, email, password);
    }
}
