package com.tumoji.tumoji.memes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class TagsRecyclerAdapter extends RecyclerView.Adapter<TagsRecyclerAdapter.ViewHolder> {
    private static final int TAG_INDEX_NONE = -1;
    private static final int TAG_INDEX_OTHER = -2;
    private static final int TYPE_UNSELECTED = 0;
    private static final int TYPE_SELECTED = 1;
    private static final int TYPE_MORE = 2;

    private ArrayList<TagModel> mTagsList = new ArrayList<>();
    private TagModel mSelectedTag = null;
    private int mSelectedTagIndex = TAG_INDEX_NONE;

    public TagsRecyclerAdapter() {
        mTagsList.add(new TagModel().withTagName("Hello"));
        mTagsList.add(new TagModel().withTagName("Tom"));
        mTagsList.add(new TagModel().withTagName("WANGnima"));
        mTagsList.add(new TagModel().withTagName("Lihailewode"));

        mSelectedTagIndex = 1;
        mSelectedTag = mTagsList.get(mSelectedTagIndex);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_MORE;
        } else if (mSelectedTagIndex != TAG_INDEX_NONE && position == 0) {
            return TYPE_SELECTED;
        } else {
            return TYPE_UNSELECTED;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_MORE) {
            holder.titleText.setText(R.string.more);
            holder.itemView.setBackgroundResource(R.drawable.background_chip_unselected);
        } else if (type == TYPE_SELECTED) {
            holder.titleText.setText(mSelectedTag.getTagName());
            holder.itemView.setBackgroundResource(R.drawable.background_chip_selected);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.background_chip_unselected);
            if (mSelectedTagIndex == TAG_INDEX_NONE) {
                holder.titleText.setText(mTagsList.get(position).getTagName());
            } else if (mSelectedTagIndex == TAG_INDEX_OTHER) {
                holder.titleText.setText(mTagsList.get(position - 1).getTagName());
            } else {
                if (mSelectedTagIndex < position) {
                    holder.titleText.setText(mTagsList.get(position).getTagName());
                } else {
                    holder.titleText.setText(mTagsList.get(position - 1).getTagName());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTagsList.size() + (mSelectedTagIndex == TAG_INDEX_OTHER ? 2 : 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;
//        public ImageButton removeButton;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.title_text);
//            removeButton = (ImageButton) itemView.findViewById(R.id.remove_button);
        }
    }
}
