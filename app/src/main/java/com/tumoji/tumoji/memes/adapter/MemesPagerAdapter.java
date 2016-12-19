package com.tumoji.tumoji.memes.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.memes.fragment.MemesListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/13/16
 */

public class MemesPagerAdapter extends FragmentPagerAdapter {
    public static final int INDEX_POPULAR = 0;
    public static final int INDEX_NEW = 1;
    public static final int PAGE_COUNT = 2;

    private List<MemesListFragment> mFragments = new ArrayList<>();
    private String[] mTitles;

    public MemesPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);

        mFragments.add(MemesListFragment.newInstance(INDEX_POPULAR));
        mFragments.add(MemesListFragment.newInstance(INDEX_NEW));

        mTitles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public void refreshMemesList(int index, List<MemeModel> memeModels, int offset) {
        if (index == INDEX_POPULAR) {
            mFragments.get(0).reloadMemesList(memeModels, offset);
        } else if (index == INDEX_NEW) {
            mFragments.get(1).reloadMemesList(memeModels, offset);
        }
    }
}
