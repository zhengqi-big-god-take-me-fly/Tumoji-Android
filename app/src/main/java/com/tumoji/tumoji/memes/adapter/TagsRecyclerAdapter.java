package com.tumoji.tumoji.memes.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
    private OnTagClickListener mListener;

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
            holder.titleText.setTextColor(Color.WHITE);
            holder.itemView.setBackgroundResource(R.drawable.background_chip_unselected);
            holder.itemView.setOnClickListener(view -> {
                if (mListener != null) {
                    mListener.onMoreTagClick();
                }
            });
        } else if (type == TYPE_SELECTED) {
            holder.titleText.setText(mSelectedTag.getTagName());
            holder.titleText.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorPrimary));
            holder.itemView.setBackgroundResource(R.drawable.background_chip_selected);
            holder.itemView.setOnClickListener(view -> {
                deselectTag();
            });
        } else {
            TagModel tagModel;
            if (mSelectedTagIndex == TAG_INDEX_NONE) {
                tagModel = mTagsList.get(position);
//                holder.titleText.setText();
            } else if (mSelectedTagIndex == TAG_INDEX_OTHER) {
                tagModel = mTagsList.get(position - 1);
//                holder.titleText.setText(mTagsList.get(position - 1).getTagName());
            } else {
                if (mSelectedTagIndex < position) {
                    tagModel = mTagsList.get(position);
//                    holder.titleText.setText(mTagsList.get(position).getTagName());
                } else {
                    tagModel = mTagsList.get(position - 1);
//                    holder.titleText.setText(mTagsList.get(position - 1).getTagName());
                }
            }
            holder.titleText.setText(tagModel.getTagName());
            holder.titleText.setTextColor(Color.WHITE);
            holder.itemView.setBackgroundResource(R.drawable.background_chip_unselected);
            holder.itemView.setOnClickListener(view -> {
                selectTag(tagModel);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTagsList.size() + (mSelectedTagIndex == TAG_INDEX_OTHER ? 2 : 1);
    }

    public void setOnTagClickListener(OnTagClickListener listener) {
        mListener = listener;
    }

    public TagModel getSelectedTag() {
        return mSelectedTag;
    }

    public void refreshTags(List<TagModel> tagModels) {
        mTagsList.clear();
        if (mSelectedTagIndex != TAG_INDEX_NONE) {
            mSelectedTagIndex = TAG_INDEX_OTHER;
        }
        for (int i = 0; i < tagModels.size(); ++i) {
            mTagsList.add(tagModels.get(i));
            if (mSelectedTagIndex != TAG_INDEX_NONE) {
                if (mSelectedTag.getTagName().equals(mTagsList.get(i).getTagName())) {
                    mSelectedTagIndex = i;
                }
            }
        }
        notifyDataSetChanged();
    }

    public void selectTag(@NonNull TagModel tagModel) {
        mSelectedTag = tagModel;
        mSelectedTagIndex = TAG_INDEX_OTHER;
        for (int i = 0; i < mTagsList.size(); ++i) {
            if (mSelectedTag.getTagName().equals(mTagsList.get(i).getTagName())) {
                mSelectedTagIndex = i;
                break;
            }
        }
        notifyDataSetChanged();
        if (mListener != null) {
            mListener.onSelectTag(mSelectedTag);
        }
    }

    public void deselectTag() {
        mSelectedTag = null;
        mSelectedTagIndex = TAG_INDEX_NONE;
        notifyDataSetChanged();
        if (mListener != null) {
            mListener.onSelectTag(mSelectedTag);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
//        public ImageButton removeButton;

        ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.title_text);
//            removeButton = (ImageButton) itemView.findViewById(R.id.remove_button);
        }
    }

    public interface OnTagClickListener {
        void onMoreTagClick();
        void onSelectTag(TagModel tagModel);
    }
}
