package com.tumoji.tumoji.account.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tumoji.tumoji.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SignInSignUpActivityFragment extends Fragment {

    public SignInSignUpActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in_sign_up, container, false);
    }
}
