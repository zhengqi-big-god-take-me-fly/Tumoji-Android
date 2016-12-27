package com.tumoji.tumoji.account.presenter;

import com.tumoji.tumoji.account.contract.ProfileContract;
import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.data.account.repository.IAccountRepository;

/**
 * Author: perqin
 * Date  : 12/18/16
 */

public class ProfilePresenter implements ProfileContract.Presenter {
    private IAccountRepository mAccountRepository;
    private ProfileContract.View mView;

    public ProfilePresenter(IAccountRepository accountRepository, ProfileContract.View view) {
        this.mAccountRepository = accountRepository;
        this.mView = view;
    }

    @Override
    public void init() {
        mView.refreshProfile(mAccountRepository.getSignedInAccount());
        mAccountRepository.updateSignedInAccount(new OnGetResultListener<AccountModel>() {
            @Override
            public void onSuccess(AccountModel result) {
                mView.refreshProfile(result);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void changeAvatar(String newImage) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void signOut() {
        mAccountRepository.signOut().subscribe(aVoid -> {
            mView.closeProfilePage();
        }, throwable -> {
            // TODO
            throw new UnsupportedOperationException("Method not implemented");
        });
    }
}
