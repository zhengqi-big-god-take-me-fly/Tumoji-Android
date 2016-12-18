package com.tumoji.tumoji.account.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tumoji.tumoji.R;

/**
 * Created by Cindy on 2016/12/17.
 */

public class SignUpExample extends Fragment {
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirm_password;
    private Button sign_up;
    private TextView error_message;
    private SignUpOnClick suoc;
    private ProgressBar progressBar;
    public interface SignUpOnClick {
        public void onSignUpClick(String username, String email, String pwd, String confirm);
}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            suoc = (SignUpOnClick)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "you must implement some methods");
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedinstanceState) {
        super.onViewCreated(view, savedinstanceState);
        username = (EditText)view.findViewById(R.id.sign_up_username);
        email = (EditText)view.findViewById(R.id.sign_up_email);
        password = (EditText)view.findViewById(R.id.sign_up_password);
        confirm_password = (EditText)view.findViewById(R.id.sign_up_confirm_password);
        error_message = (TextView)view.findViewById(R.id.sign_up_error);
        sign_up = (Button)view.findViewById(R.id.sign_up);
        progressBar = (ProgressBar)view.findViewById(R.id.sign_up_bar);
        String usernameS = username.getText().toString();
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();
        String confirmS = confirm_password.getText().toString();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suoc.onSignUpClick(usernameS, emailS, passwordS, confirmS);
            }
        });

    }
    public void usernameError() {
        error_message.setText("Username blank or invalid");
    }
    public void emailError() {
        error_message.setText("Email blank or invalid");
    }
    public void passwordError() {
        error_message.setText("Password blank or invalid");
    }
    public void confirmPasswordError() {
        error_message.setText("ConfirmPassword is not the same as the Password");
    }
    public void networkError() {
        error_message.setText("the network connection is unavailable");
    }
    public void startWait() {
        progressBar.setVisibility(View.VISIBLE);
        username.setFocusable(false);
        username.setFocusableInTouchMode(false);
        email.setFocusable(false);
        email.setFocusableInTouchMode(false);
        password.setFocusable(false);
        password.setFocusableInTouchMode(false);
        confirm_password.setFocusable(false);
        confirm_password.setFocusableInTouchMode(false);
    }
    public void stopWait() {
        progressBar.setVisibility(View.INVISIBLE);
        username.setFocusableInTouchMode(true);
        username.setFocusable(true);
        username.requestFocus();
        email.setFocusableInTouchMode(true);
        email.setFocusable(true);
        email.requestFocus();
        password.setFocusableInTouchMode(true);
        password.setFocusable(true);
        password.requestFocus();
        confirm_password.setFocusableInTouchMode(true);
        confirm_password.setFocusable(true);
        confirm_password.requestFocus();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        if (container == null) return null;
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.sign_up_fragment, container, false);
        String a = getArguments().getString("username");
        String b = getArguments().getString("email");
        EditText email = (EditText)ll.findViewById(R.id.sign_up_email);
        EditText username = (EditText) ll.findViewById(R.id.sign_up_username);
        if (a != null) {
            email.setText(b);
            username.setText(a);
        }

        return ll;
    }


}
