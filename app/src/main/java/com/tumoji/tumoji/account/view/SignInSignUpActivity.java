package com.tumoji.tumoji.account.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.account.fragment.SignInSignUpProgressFragment;
import com.tumoji.tumoji.account.presenter.SignInSignUpPresenter;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SignInSignUpActivity extends AppCompatActivity implements SignInSignUpContract.View, SignInExample.SignInOnClick, SignUpExample.SignUpOnClick, SignInSignUpProgressFragment.NextOnClick {
    private SignInSignUpContract.Presenter mPresenter;
    private SignInExample fragment1;
    private EditText username;
    private Bundle bundle;
    private FloatingActionButton mBackFab;
    private TextView mTitleText;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitleText = (TextView) findViewById(R.id.title_text);
        mBackFab = (FloatingActionButton) findViewById(R.id.fab);
        mBackFab.setOnClickListener(v -> {
            mPresenter.backToPreviousProgress();
        });

        mPresenter = new SignInSignUpPresenter(MockAccountRepository.getInstance(), this);

        mPresenter.init();
    }
    @Override
    public void pushSignInSignUpProgress() {
        mTitleText.setText(R.string.sign_in_sign_up);
        mBackFab.setVisibility(INVISIBLE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignInSignUpProgressFragment fragment = new SignInSignUpProgressFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment, "SignInSignUp");
        fragmentTransaction.addToBackStack("SignInOrSignUp");
        fragmentTransaction.commit();
    }


    @Override
    public void pushSignInProgress() {
        mTitleText.setText(R.string.sign_in);
        username = (EditText) findViewById(R.id.sign_in_sign_up_username);
        String username_email = username.getText().toString();
        bundle = new Bundle();
        bundle.putString("username_email", username_email);
        mBackFab.setVisibility(VISIBLE);
        android.app.FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragment1 = new SignInExample();
        fragment1.setArguments(bundle);
        fragmentTransaction1.addToBackStack("SignInSignUp");
        fragmentTransaction1.replace(R.id.fragment_container, fragment1, "SignIn");
        fragmentTransaction1.commit();
        SignInExample current = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
    }

    @Override
    public void pushSignUpProgress(String username, String email) {
        mTitleText.setText("Sign up");
        mBackFab.setVisibility(VISIBLE);
        android.app.FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        SignUpExample fragment2 = new SignUpExample();
        bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("email", email);
        fragment2.setArguments(bundle);
        fragmentTransaction2.replace(R.id.fragment_container, fragment2, "SignUp");
        fragmentTransaction2.addToBackStack("SignInSignUp");
        fragmentTransaction2.commit();
    }

    @Override
    public void popProgress() {
        mBackFab.setVisibility(INVISIBLE);
        fragmentManager.popBackStackImmediate();
    }

    @Override
    public void progressStartLoading() {
        SignInExample s1 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
        if(s1 != null) s1.startWait();
        SignUpExample s2 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        if (s2 != null) s2.startWait();
        SignInSignUpProgressFragment s3 = (SignInSignUpProgressFragment)fragmentManager.findFragmentByTag("SignInSignUp");
        if (s3 != null) s3.startWait();
    }

    @Override
    public void progressStopLoading() {
        SignInExample s1 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
        if(s1 != null) s1.stopWait();
        SignUpExample s2 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        if (s2 != null) s2.stopWait();
        SignInSignUpProgressFragment s3 = (SignInSignUpProgressFragment)fragmentManager.findFragmentByTag("SignInSignUp");
        if (s3 != null) s3.stopWait();
    }

    @Override
    public void showUsernameBlankOrInvalidError() {
        SignUpExample currentFragment = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        currentFragment.usernameError();
    }

    @Override
    public void showEmailBlankOrInvalidError() {
        SignUpExample currentFragment = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        currentFragment.emailError();
    }

    @Override
    public void showNetworkError() {
        SignUpExample s1 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        if (s1 != null) s1.networkError();
        SignInExample s2 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
        if (s2 != null) s2.networkError();
        SignInSignUpProgressFragment s3 = (SignInSignUpProgressFragment)fragmentManager.findFragmentByTag("SignInSignUp");
        if (s3 != null) s3.networkErrror();
    }

    @Override
    public void showPasswordBlankOrInvalidError() {
        SignUpExample currentFragment = (SignUpExample) fragmentManager.findFragmentByTag("SignIn");
        currentFragment.passwordError();
    }

    @Override
    public void showPasswordWrongError() {
        SignInExample currentFragment = (SignInExample) fragmentManager.findFragmentByTag("SignIn");
        currentFragment.passwordError();
    }

    @Override
    public void showPasswordUnconfirmedError() {
        SignUpExample currentFragment = (SignUpExample) fragmentManager.findFragmentByTag("SignIn");
        currentFragment.confirmPasswordError();
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
    public void onSignInClick(String pwd) {
        mPresenter.nextAfterSignIn(pwd);
    }

    @Override
    public void onSignUpClick(String username, String email, String pwd, String confirm) {
        mPresenter.nextAfterSignUp(username, email, pwd, confirm);
    }

    @Override
    public void onClickNext(String username) {
        mPresenter.nextAfterSignInSignUp(username);
    }
}
