package com.tumoji.tumoji.account.presenter;

import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.data.account.repository.IAccountRepository;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class SignInSignUpPresenter implements SignInSignUpContract.Presenter {
    private IAccountRepository mAccountRepository;
    private SignInSignUpContract.View mView;

    public SignInSignUpPresenter(IAccountRepository accountRepository, SignInSignUpContract.View view) {
        mAccountRepository = accountRepository;
        mView = view;
    }

    @Override
    public void init() {
        // TODO: Implement com.tumoji.tumoji.account.presenter.SignInSignUpPresenter.init
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void nextAfterSignInSignUp(String usernameOrEmail) {
        // TODO: Implement com.tumoji.tumoji.account.presenter.SignInSignUpPresenter.nextAfterSignInSignUp
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void nextAfterSignIn(String password) {
        // TODO: Implement com.tumoji.tumoji.account.presenter.SignInSignUpPresenter.nextAfterSignIn
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void nextAfterSignUp(String username, String email, String password, String passwordConfirm) {
        // TODO: Implement com.tumoji.tumoji.account.presenter.SignInSignUpPresenter.nextAfterSignUp
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void backToPreviousProgress() {
        // TODO: Implement com.tumoji.tumoji.account.presenter.SignInSignUpPresenter.backToPreviousProgress
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void stopSignInSignUp() {
        // TODO: Implement com.tumoji.tumoji.account.presenter.SignInSignUpPresenter.stopSignInSignUp
        throw new UnsupportedOperationException("Method not implemented");
    }
}
