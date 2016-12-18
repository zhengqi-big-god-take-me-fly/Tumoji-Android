package com.tumoji.tumoji.account.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
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
import android.widget.Toast;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.account.presenter.SignInSignUpPresenter;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

import org.w3c.dom.Text;

import static android.R.attr.password;
import static android.view.View.*;

public class SignInSignUpActivity extends AppCompatActivity implements SignInSignUpContract.View, SignInExample.SignInOnClick, SignUpExample.SignUpOnClick, SignInSignUpExample.NextOnClick {
    private Context context = this;
    private SignInSignUpContract.Presenter mPresenter;
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
        mPresenter = new SignInSignUpPresenter(MockAccountRepository.getInstance(), this);
        pushSignInSignOutProgress();
    }
    @Override
    public void pushSignInSignOutProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignInSignOutProgress

        try {
            fab.setVisibility(INVISIBLE);
            android.app.FragmentTransaction fragmentTransaction0 = fragmentManager.beginTransaction();
            SignInSignUpExample fragment0 = new SignInSignUpExample();
            fragmentTransaction0.replace(R.id.fragment_container, fragment0, "SignInSignUp");
            fragmentTransaction0.addToBackStack("SignInOrSignUp");
            fragmentTransaction0.commit();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }


    @Override
    public void pushSignInProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignInProgress
        try {
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
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void pushSignUpProgress(String username, String email) {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignUpProgress
        try {
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
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void popProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.popProgress
        try {
            fab.setVisibility(INVISIBLE);
            fragmentManager.popBackStackImmediate();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void progressStartLoading() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.progressStartLoading
        try {
            SignInExample s1 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
            if(s1 != null) s1.startWait();
            SignUpExample s2 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
            if (s2 != null) s2.startWait();
            SignInSignUpExample s3 = (SignInSignUpExample)fragmentManager.findFragmentByTag("SignInSignUp");
            if (s3 != null) s3.startWait();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void progressStopLoading() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.progressStopLoading
        try {
            SignInExample s1 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
            if(s1 != null) s1.stopWait();
            SignUpExample s2 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
            if (s2 != null) s2.stopWait();
            SignInSignUpExample s3 = (SignInSignUpExample)fragmentManager.findFragmentByTag("SignInSignUp");
            if (s3 != null) s3.stopWait();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void showUsernameBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showUsernameBlankOrInvalidError
        try {
            SignUpExample currentFragment = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
            currentFragment.usernameError();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void showEmailBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showEmailBlankOrInvalidError
        try {
            SignUpExample currentFragment = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
            currentFragment.emailError();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void showNetworkError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showNetworkError

        try {
            SignUpExample s1 = (SignUpExample)fragmentManager.findFragmentByTag("SignUp");
            if (s1 != null) s1.networkError();
            SignInExample s2 = (SignInExample)fragmentManager.findFragmentByTag("SignIn");
            if (s2 != null) s2.networkError();
            SignInSignUpExample s3 = (SignInSignUpExample)fragmentManager.findFragmentByTag("SignInSignUp");
            if (s3 != null) s3.networkErrror();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void showPasswordBlankOrInvalidError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordBlankOrInvalidError
        try {
            SignUpExample currentFragment = (SignUpExample) fragmentManager.findFragmentByTag("SignIn");
            currentFragment.passwordError();
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void showPasswordWrongError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordWrongError
        try {
            SignInExample currentFragment = (SignInExample) fragmentManager.findFragmentByTag("SignIn");
            currentFragment.passwordError();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }


    }

    @Override
    public void showPasswordUnconfirmedError() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.showPasswordUnconfirmedError
        try {
            SignUpExample currentFragment = (SignUpExample) fragmentManager.findFragmentByTag("SignIn");
            currentFragment.confirmPasswordError();
        }catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void finishSignIn() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.finishSignIn
        try {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            SignInSignUpActivity.this.finish();
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }

    }

    @Override
    public void finishSignUp() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.finishSignUp
        try {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            SignInSignUpActivity.this.finish();
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }

    @Override
    public void cancelSignInSignUp() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.cancelSignInSignUp
        try {
            Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
            SignInSignUpActivity.this.finish();
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Method not implemented");
        }
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
