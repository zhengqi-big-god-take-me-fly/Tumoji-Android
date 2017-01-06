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
import android.widget.Button;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.memes.adapter.SelectTagsRecyclerAdapter;

import java.util.List;

public class SelectTagsFragment extends BottomSheetDialogFragment {
    private OnFragmentInteractionListener mListener;
    private SelectTagsRecyclerAdapter mRecyclerAdapter;
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

    private RecyclerView mRecyclerView;

    public static SelectTagsFragment newInstance() {
        return new SelectTagsFragment();
    }

    public SelectTagsFragment() {
        // Required empty public constructor
    }

    public void reloadTagsList(List<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> tagModels) {
        mRecyclerAdapter.refreshList(tagModels);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerAdapter = new SelectTagsRecyclerAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onSelectTagsFragmentCreateView();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        dialog.setContentView(R.layout.fragment_select_tags);

        View sheetContentRoot = dialog.findViewById(R.id.sheet_content);
        Button doneButton = (Button) sheetContentRoot.findViewById(R.id.done_button);
        doneButton.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onFinishSelection(mRecyclerAdapter.getDataSet());
            }
            dismiss();
        });
        mRecyclerView = (RecyclerView) sheetContentRoot.findViewById(R.id.tags_recycler_view);
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

    public interface OnFragmentInteractionListener {
        void onSelectTagsFragmentCreateView();
        void onFinishSelection(List<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> tagModels);
    }
}
