package com.tumoji.tumoji.memes.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tumoji.tumoji.memes.fragment.MemesListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/13/16
 */

public class MemesPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 2;

    private List<Fragment> mFragments = new ArrayList<>();

    public MemesPagerAdapter(FragmentManager fm) {
        super(fm);

        for (int i = 0; i < PAGE_COUNT; ++i) {
            mFragments.add(null);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO: Get page title
        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            fragment = MemesListFragment.newInstance();
            mFragments.set(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
