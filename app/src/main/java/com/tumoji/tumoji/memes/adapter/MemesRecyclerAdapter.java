package com.tumoji.tumoji.memes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.model.MemeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/13/16
 */

public class MemesRecyclerAdapter extends RecyclerView.Adapter<MemesRecyclerAdapter.ViewHolder> {
    private ArrayList<MemeModel> mMemesList = new ArrayList<>();

    public MemesRecyclerAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_meme, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        throw new UnsupportedOperationException("Method not implemented");
        holder.memeImage.setImageResource(position % 2 == 0 ? R.drawable.mock_meme_0 : R.drawable.mock_meme_1);
    }

    @Override
    public int getItemCount() {
        return mMemesList.size();
    }

    public void refreshMemesList(List<MemeModel> memeModels, int offset) {
        if (mMemesList.size() < offset) {
            offset = mMemesList.size();
        }
        int removed = 0;
        for (int i = mMemesList.size() - 1; i >= offset; --i) {
            mMemesList.remove(i);
            ++removed;
        }
        notifyItemRangeRemoved(offset, removed);
        mMemesList.addAll(memeModels);
        notifyItemRangeInserted(offset, memeModels.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView memeImage;

        public ViewHolder(View itemView) {
            super(itemView);

            memeImage = (ImageView) itemView.findViewById(R.id.meme_image);
        }
    }
}
