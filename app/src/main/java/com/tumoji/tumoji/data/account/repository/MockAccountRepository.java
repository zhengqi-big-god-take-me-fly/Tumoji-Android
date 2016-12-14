package com.tumoji.tumoji.data.account.repository;

import com.tumoji.tumoji.data.account.model.AccountModel;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class MockAccountRepository implements IAccountRepository {
    private AccountModel mAccountModel;

    @Override
    public AccountModel getSignedInAccount() {
        return mAccountModel;
    }

    @Override
    public boolean hasSignedInAccount() {
        return mAccountModel != null;
    }
}
