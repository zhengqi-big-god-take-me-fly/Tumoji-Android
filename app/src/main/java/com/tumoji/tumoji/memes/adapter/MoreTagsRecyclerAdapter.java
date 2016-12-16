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
 * Date  : 12/16/16
 */

public class MoreTagsRecyclerAdapter extends RecyclerView.Adapter<MoreTagsRecyclerAdapter.ViewHolder> {
    private ArrayList<TagModel> mTagsList = new ArrayList<>();
    private OnTagClickListener mListener;

    public void setOnTagClickListener(OnTagClickListener listener) {
        mListener = listener;
    }

    public void reloadTagsList(List<TagModel> tagModels) {
        mTagsList.clear();
        mTagsList.addAll(tagModels);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_more_tags, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TagModel tagModel = mTagsList.get(position);
        holder.tagNameText.setText(tagModel.getTagName());
        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onTagClick(tagModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTagsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tagNameText;

        ViewHolder(View itemView) {
            super(itemView);

            tagNameText = (TextView) itemView.findViewById(R.id.title_text);
        }
    }

    public interface OnTagClickListener {
        void onTagClick(TagModel tagModel);
    }
}
