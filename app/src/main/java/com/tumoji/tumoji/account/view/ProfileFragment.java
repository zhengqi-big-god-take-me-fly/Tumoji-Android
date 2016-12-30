package com.tumoji.tumoji.account.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.adapter.ProfilePagerAdapter;
import com.tumoji.tumoji.account.contract.ProfileContract;
import com.tumoji.tumoji.account.fragment.ProfileInfoFragment;
import com.tumoji.tumoji.data.user.model.UserModel;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private ProfileContract.Presenter mPresenter;
    private OnFragmentInteractionListener mListener;
    private ProfilePagerAdapter mPagerAdapter;

    private ImageView mAvatarImage;
    private TextView mUsernameText;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mPagerAdapter = new ProfilePagerAdapter(getActivity().getSupportFragmentManager(), new String[]{
                getString(R.string.profile)
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAvatarImage = (ImageView) getActivity().findViewById(R.id.avatar_image);
        mUsernameText = (TextView) getActivity().findViewById(R.id.username_text);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                closeProfilePage();
                return true;
            case R.id.action_sign_out:
                mPresenter.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void refreshProfile(UserModel userModel) {
        Glide.with(getActivity()).load(userModel.getAvatarUrl()).into(mAvatarImage);
        mUsernameText.setText(userModel.getUsername());
        ((ProfileInfoFragment) mPagerAdapter.getItem(0)).refreshProfile(userModel);
    }

    @Override
    public void closeProfilePage() {
        getActivity().finish();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onProfileInfoFragmentViewCreated() {
        mPresenter.init();
    }

    public interface OnFragmentInteractionListener {
    }
}
