package com.tumoji.tumoji.memes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.model.MemeModel;

import java.util.ArrayList;

/**
 * Author: perqin
 * Date  : 12/13/16
 */

public class MemesRecyclerAdapter extends RecyclerView.Adapter<MemesRecyclerAdapter.ViewHolder> {
    private ArrayList<MemeModel> mMemesList = new ArrayList<>();

    public MemesRecyclerAdapter() {
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
        mMemesList.add(new MemeModel());
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView memeImage;

        public ViewHolder(View itemView) {
            super(itemView);

            memeImage = (ImageView) itemView.findViewById(R.id.meme_image);
        }
    }
}
