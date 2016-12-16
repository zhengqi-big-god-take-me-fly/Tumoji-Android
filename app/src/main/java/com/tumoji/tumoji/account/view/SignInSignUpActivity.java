package com.tumoji.tumoji.account.view;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.account.presenter.SignInSignUpPresenter;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

import static android.view.View.*;

public class SignInSignUpActivity extends AppCompatActivity implements SignInSignUpContract.View{
    private SignInSignUpContract.Presenter mPresenter;
    private Button next;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    FragmentManager fragmentManager = getFragmentManager();
    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }
    @Override
    public void onResume() {
        super.onResume();
        next = (Button)findViewById(R.id.signinsignup_next);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                fab.setVisibility(VISIBLE);
                pushSignInSignOutProgress();
            }
        });
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
                fab.setVisibility(INVISIBLE);
                //fragmentManager.popBackStack();
                fragmentManager.popBackStackImmediate();
            }
        });
        fab.setVisibility(INVISIBLE);
        setSupportActionBar(toolbar);
        mPresenter = new SignInSignUpPresenter(MockAccountRepository.getInstance(), this);
        mPresenter.init();
        SignInSignUpExample fragement = new SignInSignUpExample();
        fragmentTransaction.add(R.id.fragment_container, fragement);
        fragmentTransaction.commit();
        if (next != null) {
            Log.v("====", "next==========");
            next.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    fab.setVisibility(VISIBLE);
                    pushSignInSignOutProgress();
                }
            });
        } else {
            Log.v("next", "=====fuck");
        }
    }



    @Override
    public void pushSignInSignOutProgress() {
        // TODO: Implement com.tumoji.tumoji.account.view.SignInSignUpActivity.pushSignInSignOutProgress
        Log.v("000", "=================1");
        android.app.FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        Log.v("000", "=================2");
        SignInSignUpFragment.SignInExample fragment1 = new SignInSignUpFragment.SignInExample();
        fragmentTransaction1.replace(R.id.fragment_container, fragment1);
        fragmentTransaction1.addToBackStack("SignInSignUp");
        Log.v("000", "=================5");
        fragmentTransaction1.commit();
        //throw new UnsupportedOperationException("Method not implemented");
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
