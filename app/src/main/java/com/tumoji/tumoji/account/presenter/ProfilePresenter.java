package com.tumoji.tumoji.account.presenter;

import android.util.Log;

import com.tumoji.tumoji.account.contract.ProfileContract;
import com.tumoji.tumoji.data.auth.model.AuthModel;
import com.tumoji.tumoji.data.auth.repository.IAuthRepository;
import com.tumoji.tumoji.data.user.repository.IUserRepository;

import java.io.File;

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
        // Show cached profile
        mUserRepository.getUser(userId).subscribe(userModel -> {
            mView.refreshProfile(userModel);
        }, throwable -> {
            throwable.printStackTrace();
            mView.showUnexpectedError(throwable.getMessage());
        });
        // Get latest profile
        mUserRepository.updateUser(userId).subscribe(userModel -> {
            mView.refreshProfile(userModel);
        }, throwable -> {
            throwable.printStackTrace();
            mView.showUnexpectedError(throwable.getMessage());
        });
    }

    @Override
    public void changeAvatar(File file) {
        AuthModel authModel = mAuthRepository.getLocalAuth();
        mUserRepository.changeUserAvatar(authModel.getAccessToken(), authModel.getUserId(), file).subscribe(userModel -> {
            mView.refreshProfile(userModel);
        }, throwable -> {
            throwable.printStackTrace();
            mView.showUnexpectedError(throwable.getMessage());
        });
    }

    @Override
    public void signOut() {
        mAuthRepository.signOut().subscribe(aVoid -> {
            mView.closeProfilePage();
        }, throwable -> {
            Log.e("SIGN_OUT", throwable.getMessage());
            // NOTE: Never throw any error, as local auth will be removed under any condition.
//            throw new UnsupportedOperationException("Method not implemented");
        });
    }
}
