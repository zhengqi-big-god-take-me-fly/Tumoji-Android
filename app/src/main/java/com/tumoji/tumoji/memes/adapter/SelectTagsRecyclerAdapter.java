package com.tumoji.tumoji.memes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: perqin
 * Date  : 1/6/17
 */

public class SelectTagsRecyclerAdapter extends RecyclerView.Adapter<SelectTagsRecyclerAdapter.ViewHolder> {
    private ArrayList<TagModelSelectableWrapper> mDataSet = new ArrayList<>();

    public void refreshList(List<TagModelSelectableWrapper> tags) {
        mDataSet.clear();
        mDataSet.addAll(tags);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_select_tags, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tagCheck.setChecked(mDataSet.get(position).isSelected());
        holder.tagCheck.setText(mDataSet.get(position).getTagModel().getTagName());
        holder.tagCheck.setOnCheckedChangeListener((buttonView, isChecked) -> mDataSet.get(holder.getAdapterPosition()).setSelected(isChecked));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public List<TagModelSelectableWrapper> getDataSet() {
        return mDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox tagCheck;

        public ViewHolder(View itemView) {
            super(itemView);

            tagCheck = (CheckBox) itemView.findViewById(R.id.tag_check);
        }
    }

    public static class TagModelSelectableWrapper {
        private boolean selected;
        private TagModel tagModel;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public TagModel getTagModel() {
            return tagModel;
        }

        public void setTagModel(TagModel tagModel) {
            this.tagModel = tagModel;
        }
    }
}
