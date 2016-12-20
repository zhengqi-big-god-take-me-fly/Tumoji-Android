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

public class SignInProgressFragment extends Fragment implements ProgressFragment {
    private static final String ARG_USERNAME_OR_EMAIL = "USERNAME_OR_EMAIL";

    SignInFragmentInteractionListener mListener;

    private TextView mErrorText;
    private Button mSignInButton;
    private EditText mPasswordEdit;
    private EditText mUsernameOrEmailEdit;
    private ProgressBar mProgressBar;
    private String mUsernameOrEmail;

    public SignInProgressFragment() {
    }

    public static SignInProgressFragment newInstance(String usernameOrEmail) {
        SignInProgressFragment fragment = new SignInProgressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME_OR_EMAIL, usernameOrEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInFragmentInteractionListener) {
            mListener = (SignInFragmentInteractionListener) context;
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
            mUsernameOrEmail = args.getString(ARG_USERNAME_OR_EMAIL, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mErrorText = (TextView) view.findViewById(R.id.error_text);
        mUsernameOrEmailEdit = (EditText)view.findViewById(R.id.username_email_edit);
        mUsernameOrEmailEdit.setText(mUsernameOrEmail);
        mPasswordEdit = (EditText)view.findViewById(R.id.password_edit);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        mSignInButton = (Button) view.findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onSignInClick(mUsernameOrEmailEdit.getText().toString(), mPasswordEdit.getText().toString());
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
        mUsernameOrEmailEdit.setEnabled(false);
        mPasswordEdit.setEnabled(false);
    }

    @Override
    public void stopLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mUsernameOrEmailEdit.setEnabled(true);
        mPasswordEdit.setEnabled(true);
    }

    public interface SignInFragmentInteractionListener {
        void onSignInClick(String usernameOrEmail, String password);
    }
}