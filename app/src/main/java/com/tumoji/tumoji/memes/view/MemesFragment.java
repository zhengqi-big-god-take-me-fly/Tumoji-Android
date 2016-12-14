package com.tumoji.tumoji.memes.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.memes.adapter.MemesPagerAdapter;
import com.tumoji.tumoji.memes.adapter.TagsRecyclerAdapter;
import com.tumoji.tumoji.memes.contract.MemesContract;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemesFragment extends Fragment implements MemesContract.View {
    private MemesContract.Presenter mPresenter;

    private TagsRecyclerAdapter mTagsRecyclerAdapter;
    private MemesPagerAdapter mPagerAdapter;

    private RecyclerView mTagsRecyclerView;
    private ViewPager mViewPager;


    public MemesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MemesFragment.
     */
    public static MemesFragment newInstance() {
        return new MemesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTagsRecyclerAdapter = new TagsRecyclerAdapter();
        mPagerAdapter = new MemesPagerAdapter(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.init();

        mTagsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.tags_recycler_view);
        mTagsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mTagsRecyclerView.setAdapter(mTagsRecyclerAdapter);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void setPresenter(MemesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showHdMeme(MemeModel memeModel) {
        // TODO: Implement com.tumoji.tumoji.memes.view.MemesFragment.showHdMeme
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void refreshPopularMemesList(List<MemeModel> memeModels, int offset) {
        // TODO: Implement com.tumoji.tumoji.memes.view.MemesFragment.refreshPopularMemesList
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void refreshNewMemesList(List<MemeModel> memeModels, int offset) {
        // TODO: Implement com.tumoji.tumoji.memes.view.MemesFragment.refreshNewMemesList
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void refreshTagsList(List<TagModel> tagModels) {
        mTagsRecyclerAdapter.refreshTags(tagModels);
    }

    @Override
    public void refreshHdMeme(MemeModel newMemeModel) {
        // TODO: Implement com.tumoji.tumoji.memes.view.MemesFragment.refreshHdMeme
        throw new UnsupportedOperationException("Method not implemented");
    }
}
