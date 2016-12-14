package com.tumoji.tumoji.account.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.account.presenter.SignInSignUpPresenter;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

public class SignInSignUpActivity extends AppCompatActivity implements SignInSignUpContract.View {
    private SignInSignUpContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter = new SignInSignUpPresenter(MockAccountRepository.getInstance(), this);

        mPresenter.init();
    }

    @Override
    public void pushSignInSignOutProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignInSignOutProgress
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void pushSignInProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignInProgress
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void pushSignUpProgress(String username, String email) {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignUpProgress
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void popProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.popProgress
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void progressStartLoading() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.progressStartLoading
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void progressStopLoading() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.progressStopLoading
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showUsernameBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showUsernameBlankOrInvalidError
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showEmailBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showEmailBlankOrInvalidError
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showNetworkError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showNetworkError
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showPasswordBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordBlankOrInvalidError
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showPasswordWrongError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordWrongError
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showPasswordUnconfirmedError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordUnconfirmedError
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void finishSignIn() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.finishSignIn
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void finishSignUp() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.finishSignUp
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void cancelSignInSignUp() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.cancelSignInSignUp
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void setPresenter(SignInSignUpContract.Presenter presenter) {
    }
}
