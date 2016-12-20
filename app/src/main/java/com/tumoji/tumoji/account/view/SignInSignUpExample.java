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

/**
 * Created by Cindy on 2016/12/16.
 */

public class SignInSignUpExample extends Fragment {
    private TextView error_message;
    private Button next;
    private EditText username;
    private ProgressBar progressBar;
    public void networkErrror() {
        error_message.setText("the network connection is unavailable");
    }
    private NextOnClick noc;
    public interface NextOnClick {
        public void onClickNext(String username);
    }

    public void startWait() {
        progressBar.setVisibility(View.VISIBLE);
        username.setFocusable(false);
        username.setFocusableInTouchMode(false);
    }
    public void stopWait() {
        progressBar.setVisibility(View.INVISIBLE);
        username.setFocusableInTouchMode(true);
        username.setFocusable(true);
        username.requestFocus();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            noc = (NextOnClick)activity;
        } catch (ClassCastException e) {
            throw  new ClassCastException(activity.toString() + "you must implement the NextOnClick");
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedinstanceState) {
        super.onViewCreated(view, savedinstanceState);
        username = (EditText)view.findViewById(R.id.sign_in_sign_up_username);
        String a = username.getText().toString();
        progressBar = (ProgressBar)view.findViewById(R.id.sign_in_sign_up_bar);
        next = (Button)view.findViewById(R.id.sign_in_sign_up_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noc.onClickNext(a);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        if (container == null) return null;
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.sign_in_sign_up_fragment, container, false);
        return ll;
    }
}
