package com.tumoji.tumoji.data.account.repository;

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
}
