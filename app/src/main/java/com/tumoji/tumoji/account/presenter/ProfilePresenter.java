package com.tumoji.tumoji.account.presenter;

import com.tumoji.tumoji.account.contract.ProfileContract;
import com.tumoji.tumoji.data.auth.repository.IAuthRepository;
import com.tumoji.tumoji.data.user.repository.IUserRepository;

/**
 * Author: perqin
 * Date  : 12/18/16
 */

public class ProfilePresenter implements ProfileContract.Presenter {
    private IAuthRepository mAuthRepository;
    private IUserRepository mUserRepository;
    private ProfileContract.View mView;

    public ProfilePresenter(IAuthRepository accountRepository, IUserRepository userRepository, ProfileContract.View view) {
        this.mAuthRepository = accountRepository;
        this.mUserRepository = userRepository;
        this.mView = view;
    }

    @Override
    public void init() {
        String userId = mAuthRepository.getLocalAuth().getUserId();
        mUserRepository.getUser(userId).subscribe(userModel -> {
            mView.refreshProfile(userModel);
        }, throwable -> {
            // TODO
            throwable.printStackTrace();
            throw new UnsupportedOperationException("Method not implemented");
        });
        mUserRepository.updateUser(userId).subscribe(userModel -> {
            mView.refreshProfile(userModel);
        }, throwable -> {
            // TODO
            throw new UnsupportedOperationException("Method not implemented");
        });
    }

    @Override
    public void changeAvatar(String newImage) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void signOut() {
        mAuthRepository.signOut().subscribe(aVoid -> {
            mView.closeProfilePage();
        }, throwable -> {
            // TODO
            throw new UnsupportedOperationException("Method not implemented");
        });
    }
}
