package com.tumoji.tumoji.memes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.user.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: perqin
 * Date  : 1/7/17
 */

public class MemeDetailRecyclerAdapter extends RecyclerView.Adapter<MemeDetailRecyclerAdapter.ViewHolder> {
    private static final int TYPE_AUTHOR = 0;
    private static final int TYPE_TAG = 1;

    private UserModel mAuthorData = null;
    private ArrayList<TagModel> mTagsDataSet = new ArrayList<>();

    public void refreshTags(List<TagModel> tagModels) {
        mTagsDataSet.clear();
        mTagsDataSet.addAll(tagModels);
        notifyDataSetChanged();
    }

    public void refreshAuthor(UserModel author) {
        mAuthorData = author;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 && mAuthorData != null) ? TYPE_AUTHOR : TYPE_TAG;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_AUTHOR) {
            return new AuthorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_author_chip, parent, false));
        } else {
            return new TagViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_tag, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        Context context = holder.itemView.getContext();
        if (type == TYPE_AUTHOR) {
            AuthorViewHolder vh = (AuthorViewHolder) holder;
            Glide.with(context).load(mAuthorData.getAvatarUrl()).into(vh.avatarImage);
            vh.titleText.setText(mAuthorData.getUsername());
        } else {
            TagModel tag = mTagsDataSet.get(mAuthorData != null ? position - 1 : position);
            TagViewHolder vh = (TagViewHolder) holder;
            vh.titleText.setText(tag.getTagName());
        }
    }

    @Override
    public int getItemCount() {
        return mTagsDataSet.size() + (mAuthorData != null ? 1 : 0);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class AuthorViewHolder extends ViewHolder {
        CircleImageView avatarImage;
        TextView titleText;

        AuthorViewHolder(View itemView) {
            super(itemView);

            avatarImage = (CircleImageView) itemView.findViewById(R.id.avatar_image);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
        }
    }

    private static class TagViewHolder extends ViewHolder {
        TextView titleText;

        TagViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.title_text);
        }
    }
}
