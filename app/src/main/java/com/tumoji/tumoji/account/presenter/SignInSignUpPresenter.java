package com.tumoji.tumoji.account.presenter;

import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.data.auth.repository.IAuthRepository;
import com.tumoji.tumoji.data.user.repository.IUserRepository;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class SignInSignUpPresenter implements SignInSignUpContract.Presenter {
    private IAuthRepository mAuthRepository;
    private IUserRepository mUserRepository;
    private SignInSignUpContract.View mView;

    private String mUsernameOrEmail;
    private boolean mIsUsername;

    public SignInSignUpPresenter(IAuthRepository accountRepository, IUserRepository userRepository, SignInSignUpContract.View view) {
        mAuthRepository = accountRepository;
        mUserRepository = userRepository;
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
            mUserRepository.findUser(usernameOrEmail).subscribe(userModel -> {
                boolean isUsername = !usernameOrEmail.contains("@");
                String username = isUsername ? usernameOrEmail : "";
                String email = isUsername ? "" : usernameOrEmail;
                if (userModel == null) {
                    mView.pushSignUpProgress(username, email);
                } else {
                    mUsernameOrEmail = usernameOrEmail;
                    mIsUsername = isUsername;
                    mView.pushSignInProgress(usernameOrEmail);
                }
            }, throwable -> {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            });
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
                throwable.printStackTrace();
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
            mUserRepository.signUpNewUser(username, email, password)
                    .flatMap(userModel -> mAuthRepository.signIn(username, true, password))
                    .subscribe(authModel -> {
                        mView.finishSignUp();
                    }, throwable -> {
                        // TODO
                        throw new UnsupportedOperationException("Method not implemented");
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
