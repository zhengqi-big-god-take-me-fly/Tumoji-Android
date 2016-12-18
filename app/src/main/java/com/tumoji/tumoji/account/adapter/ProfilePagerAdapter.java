package com.tumoji.tumoji.account.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tumoji.tumoji.account.fragment.ProfileInfoFragment;

import java.util.ArrayList;

/**
 * Author: perqin
 * Date  : 12/18/16
 */

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[3];

    public ProfilePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);

        mFragments.add(ProfileInfoFragment.newInstance());
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
