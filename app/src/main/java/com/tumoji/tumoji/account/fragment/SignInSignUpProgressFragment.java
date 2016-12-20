package com.tumoji.tumoji.account.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tumoji.tumoji.R;

/**
 * Author: Cindy
 * Date  : 2016/12/16
 */

public class SignInSignUpProgressFragment extends Fragment {
    private TextView error_message;
    private Button mNextButton;
    private EditText mUsernameEdit;
    private ProgressBar mProgressBar;
    public void networkErrror() {
        error_message.setText("the network connection is unavailable");
    }
    private NextOnClick mListener;

    public void startWait() {
        mProgressBar.setVisibility(View.VISIBLE);
        mUsernameEdit.setFocusable(false);
        mUsernameEdit.setFocusableInTouchMode(false);
    }
    public void stopWait() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mUsernameEdit.setFocusableInTouchMode(true);
        mUsernameEdit.setFocusable(true);
        mUsernameEdit.requestFocus();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NextOnClick) {
            mListener = (NextOnClick) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NextOnClick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.sign_in_sign_up_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUsernameEdit = (EditText) view.findViewById(R.id.sign_in_sign_up_username);
        mProgressBar = (ProgressBar) view.findViewById(R.id.sign_in_sign_up_progress_bar);
        mNextButton = (Button) view.findViewById(R.id.sign_in_sign_up_next_button);
        mNextButton.setOnClickListener(v -> mListener.onClickNext(mUsernameEdit.getText().toString()));
    }

    public interface NextOnClick {
        void onClickNext(String username);
    }
}
