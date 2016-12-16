package com.tumoji.tumoji.memes.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.memes.adapter.MoreTagsRecyclerAdapter;
import com.tumoji.tumoji.memes.contract.MoreTagsContract;

import java.util.List;

public class MoreTagsFragment extends BottomSheetDialogFragment implements MoreTagsContract.View {
    private MoreTagsContract.Presenter mPresenter;
    private OnFragmentInteractionListener mListener;
    private MoreTagsRecyclerAdapter mRecyclerAdapter;
    private BottomSheetBehavior mBehavior;
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

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MoreTagsFragment.
     */
    public static MoreTagsFragment newInstance() {
        return new MoreTagsFragment();
    }

    public MoreTagsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerAdapter = new MoreTagsRecyclerAdapter();
        mRecyclerAdapter.setOnTagClickListener(this::finishSelection);
    }

    private void finishSelection(TagModel tagModel) {
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        if (mListener != null) {
            mListener.onTagClick(tagModel);
        }
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        dialog.setContentView(R.layout.fragment_more_tags);

        View sheetContentRoot = dialog.findViewById(R.id.sheet_content);
        mProgressBar = (ProgressBar) sheetContentRoot.findViewById(R.id.progress_bar);
        mRecyclerView = (RecyclerView) sheetContentRoot.findViewById(R.id.more_tags_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) sheetContentRoot.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            mBehavior = (BottomSheetBehavior) behavior;
            mBehavior.setHideable(true);
            mBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
            mBehavior.setBottomSheetCallback(mBottomSheetCallback);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter.init();

        return super.onCreateView(inflater, container, savedInstanceState);
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
    public void setPresenter(MoreTagsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading(List<TagModel> tagModels) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerAdapter.reloadTagsList(tagModels);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onTagClick(TagModel tagModel);
    }
}
