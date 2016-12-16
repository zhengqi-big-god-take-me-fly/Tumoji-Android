package com.tumoji.tumoji.data.tag.repository;

import android.content.Context;
import android.os.Handler;

import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class MockTagRepository implements ITagRepository {
    private static MockTagRepository sInstance = null;

    private Handler handler;
    private ArrayList<TagModel> tagModels = new ArrayList<>();

    public static MockTagRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MockTagRepository(context);
        }
        return sInstance;
    }

    private MockTagRepository(Context context) {
        handler = new Handler();

        // Mock data
        tagModels.add(new TagModel().withTagName("Tag A"));
        tagModels.add(new TagModel().withTagName("Tag B"));
        tagModels.add(new TagModel().withTagName("Tag C"));
        tagModels.add(new TagModel().withTagName("Tag D"));
        tagModels.add(new TagModel().withTagName("Tag E"));
        tagModels.add(new TagModel().withTagName("Long long tag"));
    }

    @Override
    public void getTagsList(OnGetTagsListListener listener) {
        handler.postDelayed(() -> {
            tagModels.add(new TagModel().withTagName("This is new 1"));
            tagModels.add(new TagModel().withTagName("This is new 2"));
            tagModels.add(new TagModel().withTagName("This is new 3"));
            listener.onSuccess(tagModels);
        }, 3 * 1000);
    }

    @Override
    public List<TagModel> getCachedTagsList() {
        return tagModels;
    }
}
