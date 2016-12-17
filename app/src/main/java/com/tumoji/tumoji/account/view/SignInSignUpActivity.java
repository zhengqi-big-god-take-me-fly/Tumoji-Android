package com.tumoji.tumoji.account.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.SignInSignUpContract;
//import com.tumoji.tumoji.account.presenter.SignInSignUpPresenter;
import com.tumoji.tumoji.account.view.Presenter;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

import org.w3c.dom.Text;

import static android.R.attr.password;
import static android.view.View.*;

public class SignInSignUpActivity extends AppCompatActivity implements SignInSignUpContract.View, SignInExample.SignInOnClick, SignUpExample.SignUpOnClick, SignInSignUpExample.NextOnClick {
    private SignInSignUpContract.Presenter mPresenter;
    //private SignInSignUpContract.Presenter.
    private SignInExample fragment1;
    private Button next;
    private EditText username;
    private Toolbar toolbar;
    private Bundle bundle;
    private FloatingActionButton fab;
    private ProgressBar progressBar;
    FragmentManager fragmentManager = getFragmentManager();
    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.backToPreviousProgress();
            }
        });
        setSupportActionBar(toolbar);
       // mPresenter = new SignInSignUpPresenter(MockAccountRepository.getInstance(), this);
        mPresenter = new Presenter(MockAccountRepository.getInstance(), this);
        pushSignInSignOutProgress();
    }



    @Override
    public void pushSignInSignOutProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignInSignOutProgress
        fab.setVisibility(INVISIBLE);

        android.app.FragmentTransaction fragmentTransaction0 = fragmentManager.beginTransaction();
        SignInSignUpExample fragment0 = new SignInSignUpExample();
        fragmentTransaction0.replace(R.id.fragment_container, fragment0, "SignInSignUp");
        fragmentTransaction0.addToBackStack("SignInOrSignUp");
        fragmentTransaction0.commit();


        //throw new UnsupportedOperationException("Method not implemented");
    }


    @Override
    public void pushSignInProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignInProgress



        username = (EditText) findViewById(R.id.sign_in_sign_up_username);
        String username_email = username.getText().toString();
        bundle = new Bundle();
        bundle.putString("username_email", username_email);
        fab.setVisibility(VISIBLE);
        android.app.FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragment1 = new SignInExample();
        fragment1.setArguments(bundle);
        fragmentTransaction1.addToBackStack("SignInSignUp");
        fragmentTransaction1.replace(R.id.fragment_container, fragment1, "SignIn");
        fragmentTransaction1.commit();
        SignInExample current = (SignInExample)fragmentManager.findFragmentByTag("SignIn");




        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void pushSignUpProgress(String username, String email) {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignUpProgress
        fab.setVisibility(VISIBLE);
        android.app.FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        SignUpExample fragment2 = new SignUpExample();
        bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("email", email);
        fragment2.setArguments(bundle);
        fragmentTransaction2.replace(R.id.fragment_container, fragment2, "SignUp");
        fragmentTransaction2.addToBackStack("SignInSignUp");
        fragmentTransaction2.commit();
        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void popProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.popProgress
        fab.setVisibility(INVISIBLE);
        fragmentManager.popBackStackImmediate();
        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void progressStartLoading() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.progressStartLoading

        SignInExample s1 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
        s1.startWait();
        SignUpExample s2 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        s2.startWait();
        SignInSignUpExample s3 = (SignInSignUpExample)fragmentManager.findFragmentByTag("SignInSignUp");
        s3.startWait();
        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void progressStopLoading() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.progressStopLoading
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showUsernameBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showUsernameBlankOrInvalidError
        SignUpExample currentFragment = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        currentFragment.usernameError();
        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showEmailBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showEmailBlankOrInvalidError
        SignUpExample currentFragment = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        currentFragment.emailError();
        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showNetworkError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showNetworkError
        SignUpExample s1 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
        s1.networkError();
        SignInExample s2 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
        s2.networkError();
        SignInSignUpExample s3 = (SignInSignUpExample)fragmentManager.findFragmentByTag("SignInSignUp");
        s3.networkErrror();

        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showPasswordBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordBlankOrInvalidError

        SignUpExample currentFragment = (SignUpExample) fragmentManager.findFragmentByTag("SignIn");
        currentFragment.passwordError();
        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showPasswordWrongError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordWrongError
        SignInExample currentFragment = (SignInExample) fragmentManager.findFragmentByTag("SignIn");
        currentFragment.passwordError();

        //throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showPasswordUnconfirmedError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordUnconfirmedError
        SignUpExample currentFragment = (SignUpExample) fragmentManager.findFragmentByTag("SignIn");
        currentFragment.confirmPasswordError();

        //throw new UnsupportedOperationException("Method not implemented");
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

    @Override
    public void onSignInClick(String pwd) {
        progressStartLoading();
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
