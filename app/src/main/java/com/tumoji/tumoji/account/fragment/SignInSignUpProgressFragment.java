package com.tumoji.tumoji.account.fragment;

import android.content.Context;
import android.os.Bundle;
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
 * Date  : 2016/12/16
 */

public class SignInSignUpProgressFragment extends Fragment implements ProgressFragment {
    private SignInSignUpFragmentInteractionListener mListener;

    private TextView mErrorText;
    private Button mNextButton;
    private EditText mUsernameEdit;
    private ProgressBar mProgressBar;

    public SignInSignUpProgressFragment() {}

    public static SignInSignUpProgressFragment newInstance() {
        return new SignInSignUpProgressFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInSignUpFragmentInteractionListener) {
            mListener = (SignInSignUpFragmentInteractionListener) context;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mErrorText = (TextView) view.findViewById(R.id.error_text);
        mUsernameEdit = (EditText) view.findViewById(R.id.username_email_edit);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mNextButton = (Button) view.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(v -> mListener.onClickNext(mUsernameEdit.getText().toString()));
    }

    @Override
    public void showError(int res) {
        mErrorText.setText(res);
    }

    @Override
    public void startLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mUsernameEdit.setEnabled(false);
    }

    @Override
    public void stopLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mUsernameEdit.setEnabled(true);
    }

    public interface SignInSignUpFragmentInteractionListener {
        void onClickNext(String username);
    }
}
