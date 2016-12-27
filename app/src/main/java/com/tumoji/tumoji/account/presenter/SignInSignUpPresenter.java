package com.tumoji.tumoji.account.presenter;

import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.common.OnGetNaiveResultListener;
import com.tumoji.tumoji.data.account.repository.IAccountRepository;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class SignInSignUpPresenter implements SignInSignUpContract.Presenter {
    private IAccountRepository mAccountRepository;
    private SignInSignUpContract.View mView;

    private String mUsernameOrEmail;
    private boolean mIsUsername;

    public SignInSignUpPresenter(IAccountRepository accountRepository, SignInSignUpContract.View view) {
        mAccountRepository = accountRepository;
        mView = view;
    }

    @Override
    public void init() {
        mView.pushSignInSignUpProgress();
    }

    @Override
    public void nextAfterSignInSignUp(String usernameOrEmail) {
        // TODO
        mUsernameOrEmail = usernameOrEmail;
        mIsUsername = true;
        mView.pushSignInProgress(mUsernameOrEmail);
    }

    @Override
    public void nextAfterSignIn(String password) {
        if (password.isEmpty()) {
            mView.showPasswordBlankOrInvalidError();
        } else {
            mAccountRepository.signIn(mUsernameOrEmail, mIsUsername, password).subscribe(o -> {
                mView.finishSignIn();
            }, throwable -> {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            });
        }
    }

    @Override
    public void nextAfterSignUp(String username, String email, String password, String passwordConfirm) {
        if (username.isEmpty()) {
            mView.showUsernameBlankOrInvalidError();
        } else if (email.isEmpty()) {
            mView.showEmailBlankOrInvalidError();
        } else if (password.isEmpty()) {
            mView.showPasswordBlankOrInvalidError();
        } else if (!password.equals(passwordConfirm)) {
            mView.showPasswordUnconfirmedError();
        } else {
            mAccountRepository.signUp(username, email, password, new OnGetNaiveResultListener() {
                @Override
                public void onSuccess() {
                    mView.finishSignUp();
                }

                @Override
                public void onFailure(int error, String msg) {
                    // TODO
                    throw new UnsupportedOperationException("Method not implemented");
                }
            });
        }
    }

    @Override
    public void backToPreviousProgress() {
        mView.popProgress();
    }

    @Override
    public void stopSignInSignUp() {
        mView.cancelSignInSignUp();
    }
}
