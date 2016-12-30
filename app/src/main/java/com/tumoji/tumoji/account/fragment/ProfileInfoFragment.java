package com.tumoji.tumoji.account.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.user.model.UserModel;

public class ProfileInfoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private TextView mEmailText;

    public ProfileInfoFragment() {
        // Required empty public constructor
    }

    public static ProfileInfoFragment newInstance() {
        return new ProfileInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEmailText = (TextView) view.findViewById(R.id.email_text);

        if (mListener != null) {
            mListener.onProfileInfoFragmentViewCreated();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void refreshProfile(UserModel userModel) {
        mEmailText.setText(userModel.getEmail());
    }

    public interface OnFragmentInteractionListener {
        void onProfileInfoFragmentViewCreated();
    }
}
