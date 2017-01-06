package com.tumoji.tumoji.memes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: perqin
 * Date  : 1/6/17
 */

public class MemeUploadTagsRecyclerAdapter extends RecyclerView.Adapter<MemeUploadTagsRecyclerAdapter.ViewHolder> {
    private ArrayList<TagModel> mDataSet = new ArrayList<>();

    public void reloadTags(List<TagModel> tagModels) {
        mDataSet.clear();
        mDataSet.addAll(tagModels);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleText.setText(mDataSet.get(position).getTagName());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.title_text);
        }
    }
}
