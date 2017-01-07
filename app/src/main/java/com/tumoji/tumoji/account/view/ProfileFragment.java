package com.tumoji.tumoji.account.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.adapter.ProfilePagerAdapter;
import com.tumoji.tumoji.account.contract.ProfileContract;
import com.tumoji.tumoji.account.fragment.ProfileInfoFragment;
import com.tumoji.tumoji.data.user.model.UserModel;
import com.tumoji.tumoji.utils.FileUtils;

import java.io.File;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

import static android.app.Activity.RESULT_OK;

@RuntimePermissions
public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {
    private static final int REQUEST_PICK_AVATAR = 1;

    private ProfileContract.Presenter mPresenter;
    private OnFragmentInteractionListener mListener;
    private ProfilePagerAdapter mPagerAdapter;

    private ImageView mAvatarImage;
    private TextView mUsernameText;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    public void onProfileInfoFragmentViewCreated() {
        mPresenter.init();
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    protected void changeAvatar(File file) {
        mPresenter.changeAvatar(file);
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
        mAvatarImage.setOnClickListener(this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_AVATAR) {
            if (resultCode == RESULT_OK) {
                ProfileFragmentPermissionsDispatcher.changeAvatarWithCheck(this, FileUtils.fromUri(getContext(), data.getData()));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void showUnexpectedError(String message) {
        Toast.makeText(getContext(), getString(R.string.unexpected_error, message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_image:
                // Pick new avatar
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), REQUEST_PICK_AVATAR);
                break;
            default:
                break;
        }
    }

    public interface OnFragmentInteractionListener {
    }
}
