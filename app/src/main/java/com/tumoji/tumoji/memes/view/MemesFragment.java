package com.tumoji.tumoji.memes.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.view.SignInSignUpActivity;
import com.tumoji.tumoji.common.SpacingItemDecoration;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.MockTagRepository;
import com.tumoji.tumoji.memes.adapter.MemesPagerAdapter;
import com.tumoji.tumoji.memes.adapter.TagsRecyclerAdapter;
import com.tumoji.tumoji.memes.contract.MemesContract;
import com.tumoji.tumoji.memes.contract.MoreTagsContract;
import com.tumoji.tumoji.memes.presenter.MoreTagsPresenter;

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
        mTagsRecyclerAdapter.setOnTagClickListener(this);
        mPagerAdapter = new MemesPagerAdapter(getActivity().getSupportFragmentManager(), new String[]{
                getString(R.string.popular),
                getString(R.string._new)
        });
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

        mPresenter.init();
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
    public void showHdMeme(MemeModel memeModel) {
        // TODO: Implement com.tumoji.tumoji.memes.view.MemesFragment.showHdMeme
        throw new UnsupportedOperationException("Method not implemented");
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
    public void refreshHdMeme(MemeModel newMemeModel) {
        // TODO: Implement com.tumoji.tumoji.memes.view.MemesFragment.refreshHdMeme
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void refreshUserInfo(AccountModel accountModel) {
        if (accountModel != null) {
            mUsernameText.setText(accountModel.getUsername());
            mEmailText.setText(accountModel.getEmail());
        } else {
            mUsernameText.setText(R.string.not_signed_in);
            mEmailText.setText(R.string.click_the_avatar_to_sign_in_or_sign_up);
        }
    }

    @Override
    public void gotoProfilePage() {
        // TODO: Implement com.tumoji.tumoji.memes.view.MemesFragment.gotoProfilePage
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void gotoSignInSignUpPage() {
        startActivity(new Intent(getActivity(), SignInSignUpActivity.class));
    }

    @Override
    public void gotoMemeUploadPage() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_image:
                mPresenter.requestOpenUserProfilePage();
                break;
            default:
                break;
        }
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

    @Override
    public void onMoreTagClick() {
        MoreTagsFragment fragment = MoreTagsFragment.newInstance();
        MoreTagsContract.Presenter presenter = new MoreTagsPresenter(MockTagRepository.getInstance(getContext()), fragment);
        fragment.setPresenter(presenter);
        fragment.show(getActivity().getSupportFragmentManager(), "MoreTagsFragment");
    }

    @Override
    public void onSelectTag(TagModel tagModel) {
        mPresenter.changeTag(tagModel);
    }

    public void onTagInMoreTagsPageClick(TagModel tagModel) {
        mTagsRecyclerAdapter.selectTag(tagModel);
    }
}
