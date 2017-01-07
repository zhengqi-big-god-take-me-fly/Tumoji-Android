package com.tumoji.tumoji.memes.view;


import android.Manifest;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.user.model.UserModel;
import com.tumoji.tumoji.memes.adapter.MemeDetailRecyclerAdapter;
import com.tumoji.tumoji.memes.contract.MemeDetailContract;

import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MemeDetailFragment extends BottomSheetDialogFragment implements MemeDetailContract.View, View.OnClickListener {
    private static final String ARG_MEME_ID = "MEME_ID";

    private MemeDetailContract.Presenter mPresenter;
    private MemeDetailRecyclerAdapter mRecyclerAdapter;
    private String mMemeId;
    private MemeModel mMemeModel;
    private BottomSheetBehavior mBottomSheetBehavior;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    private ImageView mMemeHdImage;
    private TextView mMemeNameText;
    private RecyclerView mUploaderAndTagsRecyclerView;
    private FloatingActionButton mSaveButton;
    private RelativeLayout mLikeButtonLayout;
    private ImageView mLikeIconImage;
    private TextView mLikeTitleText;
    private TextView mLikeCountText;
    private RelativeLayout mReportButtonLayout;
    private ImageView mReportIconImage;
    private TextView mReportTitleText;
    private TextView mReportCountText;

    public static MemeDetailFragment newInstance(String memeId) {
        MemeDetailFragment fragment = new MemeDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MEME_ID, memeId);
        fragment.setArguments(args);
        return fragment;
    }

    public MemeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMemeId = getArguments().getString(ARG_MEME_ID);
        }

        mRecyclerAdapter = new MemeDetailRecyclerAdapter();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = View.inflate(getContext(), R.layout.fragment_meme_detail, null);
        dialog.setContentView(view);

        mMemeHdImage = (ImageView) view.findViewById(R.id.meme_hd_image);
        mMemeNameText = (TextView) view.findViewById(R.id.meme_name_text);
        mUploaderAndTagsRecyclerView = (RecyclerView) view.findViewById(R.id.uploader_and_tags_recycler_view);
        mUploaderAndTagsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mUploaderAndTagsRecyclerView.setAdapter(mRecyclerAdapter);
        mSaveButton = (FloatingActionButton) view.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(this);
        mLikeButtonLayout = (RelativeLayout) view.findViewById(R.id.like_button_layout);
        mLikeButtonLayout.setOnClickListener(this);
        mLikeIconImage = (ImageView) view.findViewById(R.id.like_icon_image);
        mLikeTitleText = (TextView) view.findViewById(R.id.like_title_text);
        mLikeCountText = (TextView) view.findViewById(R.id.like_count_text);
        mReportButtonLayout = (RelativeLayout) view.findViewById(R.id.report_button_layout);
        mReportButtonLayout.setOnClickListener(this);
        mReportIconImage = (ImageView) view.findViewById(R.id.report_icon_text);
        mReportTitleText = (TextView) view.findViewById(R.id.report_title_text);
        mReportCountText = (TextView) view.findViewById(R.id.report_count_text);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            mBottomSheetBehavior = (BottomSheetBehavior) behavior;
            mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetCallback);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter.init(mMemeId);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void refreshMemeDetail(MemeModel memeModel) {
        // TODO: Other meme detail
        // Refresh meme image, title, likes, reports, downloaded
        mMemeModel = memeModel;
//        Glide.with(getActivity()).load(memeModel.getImageUrl()).into(mMemeHdImage);
        Glide.with(getActivity()).load(memeModel.getMemeUri()).into(mMemeHdImage);
        refreshMemeName(memeModel.getTitle());
        refreshMemeLikeStatus(memeModel.isLiked(), memeModel.getLikeCount());
        refreshMemeReportStatus(memeModel.isReported(), memeModel.getReportCount());
        mSaveButton.setVisibility(memeModel.isDownloaded() ? View.GONE : View.VISIBLE);
    }

    @Override
    public void refreshMemeName(String name) {
        mMemeNameText.setText(name);
    }

    @Override
    public void refreshMemeTags(List<TagModel> tagModels) {
        mRecyclerAdapter.refreshTags(tagModels);
    }

    @Override
    public void refreshMemeLikeStatus(boolean like, int count) {
        mLikeTitleText.setText(R.string.like_it);
        mLikeCountText.setText(getString(R.string.count_liked, count));
        mLikeIconImage.setImageResource(like ? R.drawable.ic_liked_24dp : R.drawable.ic_unliked_black_24dp);
    }

    @Override
    public void refreshMemeReportStatus(boolean report, int count) {
        mReportTitleText.setText(R.string.report_it);
        mReportCountText.setText(getString(R.string.count_reported, count));
        mReportIconImage.setImageResource(report ? R.drawable.ic_reported_black_24dp : R.drawable.ic_unreported_black_24dp);
    }

    @Override
    public void refreshMemeAuthor(UserModel userModel) {
        mRecyclerAdapter.refreshAuthor(userModel);
    }

    @Override
    public void setPresenter(MemeDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                MemeDetailFragmentPermissionsDispatcher.handleSavingMemeWithCheck(this, mMemeModel);
                break;
            case R.id.like_button_layout:
                if (mMemeModel.isLiked()) {
                    mPresenter.unlikeMeme(mMemeModel);
                } else {
                    mPresenter.likeMeme(mMemeModel);
                }
                break;
            case R.id.report_button_layout:
                // TODO: Report feature
//                if (mMemeModel.isReported()) {
//                    Toast.makeText(getContext(), R.string.you_have_already_reported_this_meme, Toast.LENGTH_SHORT).show();
//                } else {
//                    mPresenter.reportMeme(mMemeModel, "");
//                }
                Toast.makeText(getContext(), R.string.oops_this_feature_is_temporarily_unavailable, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void handleSavingMeme(MemeModel memeModel) {
        mPresenter.saveMeme(memeModel);
    }
}
