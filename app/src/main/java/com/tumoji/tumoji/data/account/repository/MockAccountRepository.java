package com.tumoji.tumoji.data.account.repository;

import android.os.Handler;

import com.tumoji.tumoji.common.OnGetNaiveResultListener;
import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.account.model.AccountModel;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class MockAccountRepository implements IAccountRepository {
    private static MockAccountRepository sInstance;

    private AccountModel mAccountModel;

    public static MockAccountRepository getInstance() {
        if (sInstance == null) {
            sInstance = new MockAccountRepository();
        }
        return sInstance;
    }

    private MockAccountRepository() {
        mAccountModel = new AccountModel().withUsername("Perqin").withEmail("original@email.com").withAvatarUrl("http://qcloud.perqin.com/shadow.png");
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
    public void updateSignedInAccount(OnGetResultListener<AccountModel> listener) {
        new Handler().postDelayed(() -> {
            mAccountModel.setUsername("Perqin Ultimate");
            mAccountModel.setEmail("another@email.com");
            listener.onSuccess(mAccountModel);
        }, 600);
    }

    @Override
    public void signOut(OnGetNaiveResultListener listener) {
        new Handler().postDelayed(() -> {
            mAccountModel = null;
            listener.onSuccess();
        }, 600);
    }

    @Override
    public void signUp(String username, String email, String password, OnGetNaiveResultListener listener) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void signIn(String usernameOrEmail, boolean isUsername, OnGetNaiveResultListener listener) {
        // TODO
        new Handler().postDelayed(listener::onSuccess, 4000);
    }
}
