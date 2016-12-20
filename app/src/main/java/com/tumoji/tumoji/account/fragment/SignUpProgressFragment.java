package com.tumoji.tumoji.account.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.common.ProgressFragment;

/**
 * Author: Cindy
 * Date  : 2016/12/17
 */

public class SignUpProgressFragment extends Fragment implements ProgressFragment {
    private static final String ARG_USERNAME = "USERNAME";
    private static final String ARG_EMAIL = "EMAIL";

    private SignUpFragmentInteractionListener mListener;
    private String mUsername;
    private String mEmail;

    private EditText mUsernameEdit;
    private EditText mEmailEdit;
    private EditText mPasswordEdit;
    private EditText mConfirmPasswordEdit;
    private Button mSignUpButton;
    private TextView mErrorText;
    private ProgressBar mProgressBar;

    public SignUpProgressFragment() {
    }

    public static SignUpProgressFragment newInstance(String username, String email) {
        SignUpProgressFragment fragment = new SignUpProgressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        args.putString(ARG_EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignUpFragmentInteractionListener) {
            mListener = (SignUpFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SignInSignUpFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mUsername = args.getString(ARG_USERNAME, "");
            mEmail = args.getString(ARG_EMAIL, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mErrorText = (TextView)view.findViewById(R.id.error_text);
        mUsernameEdit = (EditText)view.findViewById(R.id.username_edit);
        mUsernameEdit.setText(mUsername);
        mEmailEdit = (EditText)view.findViewById(R.id.email_edit);
        mEmailEdit.setText(mEmail);
        mPasswordEdit = (EditText)view.findViewById(R.id.password_edit);
        mConfirmPasswordEdit = (EditText)view.findViewById(R.id.confirm_password_edit);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        mSignUpButton = (Button)view.findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onSignUpClick(
                        mUsernameEdit.getText().toString(),
                        mEmailEdit.getText().toString(),
                        mPasswordEdit.getText().toString(),
                        mConfirmPasswordEdit.getText().toString()
                );
            }
        });
    }

    @Override
    public void showError(int res) {
        mErrorText.setText(res);
    }

    @Override
    public void startLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mUsernameEdit.setEnabled(false);
        mEmailEdit.setEnabled(false);
        mPasswordEdit.setEnabled(false);
        mConfirmPasswordEdit.setEnabled(false);
    }

    @Override
    public void stopLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mUsernameEdit.setEnabled(true);
        mEmailEdit.setEnabled(true);
        mPasswordEdit.setEnabled(true);
        mConfirmPasswordEdit.setEnabled(true);
    }

    public interface SignUpFragmentInteractionListener {
        void onSignUpClick(String username, String email, String password, String confirm);
    }
}
