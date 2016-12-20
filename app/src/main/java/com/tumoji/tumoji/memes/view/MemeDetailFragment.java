package com.tumoji.tumoji.memes.view;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.memes.contract.MemeDetailContract;

import java.util.List;

public class MemeDetailFragment extends BottomSheetDialogFragment implements MemeDetailContract.View {
    private static final String ARG_MEME_ID = "MEME_ID";

    private MemeDetailContract.Presenter mPresenter;
    private String mMemeId;
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
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = View.inflate(getContext(), R.layout.fragment_meme_detail, null);
        dialog.setContentView(view);

        mMemeHdImage = (ImageView) view.findViewById(R.id.meme_hd_image);
        mMemeNameText = (TextView) view.findViewById(R.id.meme_name_text);
        mUploaderAndTagsRecyclerView = (RecyclerView) view.findViewById(R.id.uploader_and_tags_recycler_view);
        mLikeButtonLayout = (RelativeLayout) view.findViewById(R.id.like_button_layout);
        mLikeIconImage = (ImageView) view.findViewById(R.id.like_icon_image);
        mLikeTitleText = (TextView) view.findViewById(R.id.like_title_text);
        mLikeCountText = (TextView) view.findViewById(R.id.like_count_text);
        mReportButtonLayout = (RelativeLayout) view.findViewById(R.id.report_button_layout);
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
        Glide.with(getActivity()).load(memeModel.getImageUrl()).into(mMemeHdImage);
        refreshMemeName(memeModel.getTitle());
        refreshMemeLikeStatus(memeModel.isLiked(), memeModel.getLikeCount());
        refreshMemeReportStatus(memeModel.isReported(), memeModel.getReportCount());
    }

    @Override
    public void refreshMemeName(String name) {
        mMemeNameText.setText(name);
    }

    @Override
    public void refreshMemeTags(List<TagModel> tagModels) {
        throw new UnsupportedOperationException("Method not implemented");
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
    public void setPresenter(MemeDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
