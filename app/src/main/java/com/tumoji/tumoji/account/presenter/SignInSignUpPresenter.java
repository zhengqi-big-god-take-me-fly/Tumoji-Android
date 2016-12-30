package com.tumoji.tumoji.account.presenter;

import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.data.auth.repository.IAuthRepository;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class SignInSignUpPresenter implements SignInSignUpContract.Presenter {
    private IAuthRepository mAuthRepository;
    private SignInSignUpContract.View mView;

    private String mUsernameOrEmail;
    private boolean mIsUsername;

    public SignInSignUpPresenter(IAuthRepository accountRepository, SignInSignUpContract.View view) {
        mAuthRepository = accountRepository;
        mView = view;
    }

    @Override
    public void init() {
        mView.pushSignInSignUpProgress();
    }

    @Override
    public void nextAfterSignInSignUp(String usernameOrEmail) {
        if (usernameOrEmail.isEmpty()) {
            mView.showUsernameOrEmailBlankOrInvalidError();
        } else {
            mUsernameOrEmail = usernameOrEmail;
            // TODO: Add validation
            mIsUsername = !usernameOrEmail.contains("@");
            // TODO: find account with UserRepository
//            mAuthRepository.findAccount(usernameOrEmail).subscribe(findAccountResultModel -> {
//                if (findAccountResultModel.getResult() == FindAccountResultModel.RESULT_NOT_FOUND) {
//                    mView.pushSignUpProgress(mIsUsername ? usernameOrEmail : "", mIsUsername ? "" : usernameOrEmail);
//                } else {
//                    mView.pushSignInProgress(usernameOrEmail);
//                }
//            }, throwable -> {
//                // TODO
//                throw new UnsupportedOperationException("Method not implemented");
//            });
        }
    }

    @Override
    public void nextAfterSignIn(String password) {
        if (password.isEmpty()) {
            mView.showPasswordBlankOrInvalidError();
        } else {
            mAuthRepository.signIn(mUsernameOrEmail, mIsUsername, password).subscribe(o -> {
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
            // TODO: Create user with UserRepository
//            mAuthRepository.signUp(username, email, password, new OnGetNaiveResultListener() {
//                @Override
//                public void onSuccess() {
//                    mView.finishSignUp();
//                }
//
//                @Override
//                public void onFailure(int error, String msg) {
//                    // TODO
//                    throw new UnsupportedOperationException("Method not implemented");
//                }
//            });
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
