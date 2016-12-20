package com.tumoji.tumoji.memes.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.activity.ProfileActivity;
import com.tumoji.tumoji.account.view.SignInSignUpActivity;
import com.tumoji.tumoji.common.SpacingItemDecoration;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.meme.repository.MockMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.MockTagRepository;
import com.tumoji.tumoji.memes.activity.MemeUploadActivity;
import com.tumoji.tumoji.memes.adapter.MemesPagerAdapter;
import com.tumoji.tumoji.memes.adapter.TagsRecyclerAdapter;
import com.tumoji.tumoji.memes.contract.MemeDetailContract;
import com.tumoji.tumoji.memes.contract.MemesContract;
import com.tumoji.tumoji.memes.contract.MoreTagsContract;
import com.tumoji.tumoji.memes.presenter.MemeDetailPresenter;
import com.tumoji.tumoji.memes.presenter.MoreTagsPresenter;

import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemesFragment extends Fragment implements MemesContract.View, View.OnClickListener, TagsRecyclerAdapter.OnTagClickListener {
    private MemesContract.Presenter mPresenter;
    private TagsRecyclerAdapter mTagsRecyclerAdapter;
    private MemesPagerAdapter mPagerAdapter;

    // Nav Drawer
    private CircleImageView mAvatarImage;
    private TextView mUsernameText;
    private TextView mEmailText;
    // Appbar
    private RecyclerView mTagsRecyclerView;
    // This fragment
    private ViewPager mViewPager;
    private FloatingActionButton mUploadFab;
    private boolean[] mPagesInitialized;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MemesFragment.
     */
    public static MemesFragment newInstance() {
        return new MemesFragment();
    }


    public MemesFragment() {
        // Required empty public constructor
    }

    public void onRefreshMemesList(int index) {
        TagModel tagModel = mTagsRecyclerAdapter.getSelectedTag();
        if (index == MemesPagerAdapter.INDEX_POPULAR) {
            if (tagModel != null) {
                mPresenter.updatePopularMemesListOfTag(0, tagModel);
            } else {
                mPresenter.updatePopularMemesList(0);
            }
        } else if (index == MemesPagerAdapter.INDEX_NEW) {
            if (tagModel != null) {
                mPresenter.updateNewMemesListOfTag(0, tagModel);
            } else {
                mPresenter.updateNewMemesList(0);
            }
        }
    }

    public void onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_settings) {
            // TODO
//            startActivity(new Intent(getContext(), SettingsActivity.class));
        }
    }

    public void onLoadMore(int index, int offset) {
        Log.i("MemesFrag", "On Load More");
        TagModel tagModel = mTagsRecyclerAdapter.getSelectedTag();
        if (index == MemesPagerAdapter.INDEX_POPULAR) {
            if (tagModel != null) {
                mPresenter.updatePopularMemesListOfTag(offset, tagModel);
            } else {
                mPresenter.updatePopularMemesList(offset);
            }
        } else if (index == MemesPagerAdapter.INDEX_NEW) {
            if (tagModel != null) {
                mPresenter.updateNewMemesListOfTag(offset, tagModel);
            } else {
                mPresenter.updateNewMemesList(offset);
            }
        }
    }

    public void onTagInMoreTagsPageClick(TagModel tagModel) {
        mTagsRecyclerAdapter.selectTag(tagModel);
    }

    public void onMemeClick(MemeModel memeModel) {
        MemeDetailFragment detailFragment = MemeDetailFragment.newInstance(memeModel.getMemeId());
        MemeDetailContract.Presenter presenter = new MemeDetailPresenter(MockMemeRepository.getInstance(getContext()), detailFragment);
        detailFragment.setPresenter(presenter);
        detailFragment.show(getActivity().getSupportFragmentManager(), "MemeDetailFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTagsRecyclerAdapter = new TagsRecyclerAdapter();
        mTagsRecyclerAdapter.setOnTagClickListener(this);
        mPagerAdapter = new MemesPagerAdapter(getActivity().getSupportFragmentManager(), new String[]{
                getString(R.string.popular),
                getString(R.string._new)
        });
        mPagesInitialized = new boolean[MemesPagerAdapter.PAGE_COUNT];
        Arrays.fill(mPagesInitialized, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Nav Drawer
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        View navHeaderView = navigationView.getHeaderView(0);
        mAvatarImage = (CircleImageView) navHeaderView.findViewById(R.id.avatar_image);
        mAvatarImage.setOnClickListener(this);
        mUsernameText = (TextView) navHeaderView.findViewById(R.id.username_text);
        mEmailText = (TextView) navHeaderView.findViewById(R.id.email_text);
        // This fragment
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerAdapter);
        // Appbar
        mTagsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.tags_recycler_view);
        mTagsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mTagsRecyclerView.addItemDecoration(new SpacingItemDecoration(24));
        mTagsRecyclerView.setAdapter(mTagsRecyclerAdapter);
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        // Others
        mUploadFab = (FloatingActionButton) getActivity().findViewById(R.id.upload_fab);
        mUploadFab.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.viewResume();
    }

    @Override
    public void setPresenter(MemesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void refreshPopularMemesList(List<MemeModel> memeModels, int offset) {
        mPagerAdapter.refreshMemesList(MemesPagerAdapter.INDEX_POPULAR, memeModels, offset);
    }

    @Override
    public void refreshNewMemesList(List<MemeModel> memeModels, int offset) {
        mPagerAdapter.refreshMemesList(MemesPagerAdapter.INDEX_NEW, memeModels, offset);
    }

    @Override
    public void refreshTagsList(List<TagModel> tagModels) {
        mTagsRecyclerAdapter.refreshTags(tagModels);
    }

    @Override
    public void refreshUserInfo(AccountModel accountModel) {
        if (accountModel != null) {
            mUsernameText.setText(accountModel.getUsername());
            mEmailText.setText(accountModel.getEmail());
            Glide.with(getActivity()).load(accountModel.getAvatarUrl()).into(mAvatarImage);
        } else {
            mUsernameText.setText(R.string.not_signed_in);
            mEmailText.setText(R.string.click_the_avatar_to_sign_in_or_sign_up);
        }
    }

    @Override
    public void gotoProfilePage() {
        startActivity(new Intent(getActivity(), ProfileActivity.class));
    }

    @Override
    public void gotoSignInSignUpPage() {
        startActivity(new Intent(getActivity(), SignInSignUpActivity.class));
    }

    @Override
    public void gotoMemeUploadPage() {
        startActivity(new Intent(getActivity(), MemeUploadActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_image:
                mPresenter.requestOpenUserProfilePage();
                break;
            case R.id.upload_fab:
                mPresenter.requestUploadingMeme();
                break;
            default:
                break;
        }
    }

    @Override
    public void onMoreTagClick() {
        MoreTagsFragment fragment = MoreTagsFragment.newInstance();
        MoreTagsContract.Presenter presenter = new MoreTagsPresenter(MockTagRepository.getInstance(getContext()), fragment);
        fragment.setPresenter(presenter);
        fragment.show(getActivity().getSupportFragmentManager(), "MoreTagsFragment");
    }

    @Override
    public void onSelectTag(TagModel tagModel) {
        mTagsRecyclerView.smoothScrollToPosition(0);
        mPresenter.changeTag(tagModel);
    }

    public void onListFragmentViewCreated(int index) {
        mPagesInitialized[index] = true;
        boolean allInitialized = true;
        for (boolean initialized : mPagesInitialized) {
            if (!initialized) {
                allInitialized = false;
                break;
            }
        }
        if (allInitialized) {
            mPresenter.init();
        }
    }
}
