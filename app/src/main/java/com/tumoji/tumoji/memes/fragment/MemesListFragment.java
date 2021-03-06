package com.tumoji.tumoji.memes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.memes.adapter.MemesRecyclerAdapter;

import java.util.Arrays;
import java.util.List;

public class MemesListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MemesRecyclerAdapter.OnMemeClickListener {
    private static final String ARG_INDEX = "INDEX";

    private OnFragmentInteractionListener mListener;
    private MemesRecyclerAdapter mMemesRecyclerAdapter;
    private int mIndex;
    private boolean mIsLoadingMore;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    public static MemesListFragment newInstance(int index) {
        MemesListFragment memesListFragment = new MemesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        memesListFragment.setArguments(args);
        return memesListFragment;
    }

    public MemesListFragment() {
        // Required empty public constructor
    }

    public void reloadMemesList(List<MemeModel> memeModels, int offset) {
        mRecyclerView.post(() -> {
            mMemesRecyclerAdapter.refreshMemesList(memeModels, offset);
        });
//        mMemesRecyclerAdapter.refreshMemesList(memeModels, offset);
        if (mIsLoadingMore) {
            mIsLoadingMore = false;
            mRecyclerView.addOnScrollListener(new OnRecyclerViewScrollListener());
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mIndex = args.getInt(ARG_INDEX);

        mIsLoadingMore = false;
        mMemesRecyclerAdapter = new MemesRecyclerAdapter();
        mMemesRecyclerAdapter.setOnMemeClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memes_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mMemesRecyclerAdapter);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerViewItemTouchListener());

        if (mListener != null) {
            mListener.onListFragmentViewCreated(mIndex);
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

    @Override
    public void onRefresh() {
        if (mListener != null) {
            mListener.onRefreshMemesList(mIndex);
        }
    }

    @Override
    public void onMemeClick(MemeModel memeModel) {
        if (mListener != null) {
            mListener.onMemeClick(memeModel);
        }
    }

    private class OnRecyclerViewItemTouchListener extends RecyclerView.SimpleOnItemTouchListener {
        private void disableLoadingMore() {
            mRecyclerView.clearOnScrollListeners();
        }

        private void enableLoadingMore() {
            mRecyclerView.addOnScrollListener(new OnRecyclerViewScrollListener());
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                enableLoadingMore();
            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                disableLoadingMore();
            }
            return super.onInterceptTouchEvent(rv, event);
        }
    }

    private class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        private static final int MAXIMUM_RELOAD_ROW = 2;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (mSwipeRefreshLayout.isRefreshing() || mIsLoadingMore) {
                return;
            }

            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();
            int total = layoutManager.getItemCount();
            int[] firstPositions = new int[layoutManager.getSpanCount()];
            layoutManager.findLastVisibleItemPositions(firstPositions);
            Arrays.sort(firstPositions);
            if (total - firstPositions[firstPositions.length - 1] < MAXIMUM_RELOAD_ROW * firstPositions.length) {
                // Start loading more
                if (mListener != null) {
                    mIsLoadingMore = true;
                    mRecyclerView.removeOnScrollListener(this);
                    mListener.onLoadMore(mIndex, mMemesRecyclerAdapter.getItemCount());
                }
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onListFragmentViewCreated(int index);
        void onRefreshMemesList(int index);
        void onLoadMore(int index, int offset);
        void onMemeClick(MemeModel memeModel);
    }
}
