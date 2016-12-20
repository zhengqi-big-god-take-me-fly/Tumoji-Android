package com.tumoji.tumoji.account.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.SignInSignUpContract;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

/**
 * Created by Cindy on 2016/12/17.
 */
public class SignInExample extends Fragment {
    private TextView error_message;
    private Button sign_in;
    private EditText sign_in_password;
    private EditText sign_in_username;
    private ProgressBar progressBar;
    public void passwordError() {
        error_message.setText("the password is wrong and SignIn is failed");
    }
    public void networkError() {
        error_message.setText("the network connection is unavailable");
    }
    SignInOnClick sioc;
    public interface SignInOnClick {
        public void onSignInClick(String pwd);
    }
    public void startWait() {
        progressBar.setVisibility(View.VISIBLE);
        sign_in_username.setFocusable(false);
        sign_in_username.setFocusableInTouchMode(false);
        sign_in_password.setFocusable(false);
        sign_in_password.setFocusableInTouchMode(false);
    }
    public void stopWait() {
        progressBar.setVisibility(View.INVISIBLE);
        sign_in_username.setFocusableInTouchMode(true);
        sign_in_username.setFocusable(true);
        sign_in_username.requestFocus();
        sign_in_password.setFocusableInTouchMode(true);
        sign_in_password.setFocusable(true);
        sign_in_password.requestFocus();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            sioc = (SignInOnClick)activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement SignInOnClick");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedinstanceState) {
        super.onViewCreated(view, savedinstanceState);
        progressBar = (ProgressBar)view.findViewById(R.id.sign_in_bar);
        error_message = (TextView) view.findViewById(R.id.sign_in_error);
        sign_in_password = (EditText)view.findViewById(R.id.sign_in_password);
        sign_in_username = (EditText)view.findViewById(R.id.sign_in_username);
        sign_in = (Button) view.findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sioc.onSignInClick(sign_in_password.getText().toString());
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        if (container == null) return null;
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.sign_in_fragment, container, false);
        //password = (EditText)ll.findViewById(R.id.sign_in_password);

                EditText username = (EditText)ll.findViewById(R.id.sign_in_username);
        String a = getArguments().getString("username_email");
        if (a != null) {
            username.setText(a);
        }


        return ll;

    }
}