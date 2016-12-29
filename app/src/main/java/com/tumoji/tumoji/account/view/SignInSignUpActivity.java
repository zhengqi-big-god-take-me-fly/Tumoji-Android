package com.tumoji.tumoji.account.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.account.fragment.SignInProgressFragment;
import com.tumoji.tumoji.account.fragment.SignInSignUpProgressFragment;
import com.tumoji.tumoji.account.fragment.SignUpProgressFragment;
import com.tumoji.tumoji.account.presenter.SignInSignUpPresenter;
import com.tumoji.tumoji.common.ProgressFragment;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SignInSignUpActivity extends AppCompatActivity implements SignInSignUpContract.View, SignInProgressFragment.SignInFragmentInteractionListener, SignUpProgressFragment.SignUpFragmentInteractionListener, SignInSignUpProgressFragment.SignInSignUpFragmentInteractionListener {
    private SignInSignUpContract.Presenter mPresenter;

    private FloatingActionButton mBackFab;
    private TextView mTitleText;

    private void performBack() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment instanceof SignInSignUpProgressFragment) {
            mPresenter.stopSignInSignUp();
        } else {
            mPresenter.backToPreviousProgress();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        }

        mTitleText = (TextView) findViewById(R.id.title_text);
        mBackFab = (FloatingActionButton) findViewById(R.id.fab);
        mBackFab.setOnClickListener(v -> performBack());

        mPresenter = new SignInSignUpPresenter(MockAccountRepository.getInstance(this), this);

        mPresenter.init();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            performBack();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mPresenter.stopSignInSignUp();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void pushSignInSignUpProgress() {
        mTitleText.setText(R.string.sign_in_sign_up);
        mBackFab.setVisibility(INVISIBLE);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        SignInSignUpProgressFragment fragment = new SignInSignUpProgressFragment();
//        transaction.replace(R.id.fragment_container, fragment, "SignInSignUp");
//        transaction.addToBackStack("SignInOrSignUp");
//        transaction.commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SignInSignUpProgressFragment.newInstance())
                .addToBackStack("SignInSignUp")
                .commit();
    }

    @Override
    public void pushSignInProgress(String usernameOrEmail) {
        mTitleText.setText(R.string.sign_in);
        mBackFab.setVisibility(VISIBLE);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        SignInProgressFragment fragment = SignInProgressFragment.newInstance(usernameOrEmail);
//        transaction.addToBackStack("SignInSignUp");
//        transaction.replace(R.id.fragment_container, fragment, "SignIn");
//        transaction.commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SignInProgressFragment.newInstance(usernameOrEmail))
                .addToBackStack("SignIn")
                .commit();
    }

    @Override
    public void pushSignUpProgress(String username, String email) {
        mTitleText.setText(R.string.sign_up);
        mBackFab.setVisibility(VISIBLE);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        SignUpProgressFragment fragment = SignUpProgressFragment.newInstance(username, email);
//        transaction.replace(R.id.fragment_container, fragment, "SignUp");
//        transaction.addToBackStack("SignInSignUp");
//        transaction.commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SignUpProgressFragment.newInstance(username, email))
                .addToBackStack("SignUp")
                .commit();
    }

    @Override
    public void popProgress() {
        mBackFab.setVisibility(INVISIBLE);
//        getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void progressStartLoading() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.startLoading();
        }
    }

    @Override
    public void progressStopLoading() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.stopLoading();
        }
    }

    @Override
    public void showUsernameOrEmailBlankOrInvalidError() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.showError(R.string.the_username_or_email_field_is_empty_or_invalid);
        }
    }

    @Override
    public void showUsernameBlankOrInvalidError() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.showError(R.string.the_username_field_is_empty_or_invalid);
        }
    }

    @Override
    public void showEmailBlankOrInvalidError() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.showError(R.string.the_email_field_is_empty_or_invalid);
        }
    }

    @Override
    public void showNetworkError() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.showError(R.string.network_error);
        }
    }

    @Override
    public void showPasswordBlankOrInvalidError() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.showError(R.string.the_password_field_is_empty_or_invalid);
        }
    }

    @Override
    public void showPasswordWrongError() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.showError(R.string.password_unconfirmed);
        }
    }

    @Override
    public void showPasswordUnconfirmedError() {
        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (progressFragment != null) {
            progressFragment.showError(R.string.password_unconfirmed);
        }
    }

    @Override
    public void finishSignIn() {
        Toast.makeText(this, R.string.sign_in_successfully, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void finishSignUp() {
        Toast.makeText(this, R.string.sign_up_successfully, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void cancelSignInSignUp() {
        finish();
    }

    @Override
    public void setPresenter(SignInSignUpContract.Presenter presenter) {
    }

    @Override
    public void onClickNext(String username) {
        mPresenter.nextAfterSignInSignUp(username);
    }

    @Override
    public void onSignInClick(String username, String password) {
        mPresenter.nextAfterSignIn(password);
    }

    @Override
    public void onSignUpClick(String username, String email, String password, String confirm) {
        mPresenter.nextAfterSignUp(username, email, password, confirm);
    }
}
