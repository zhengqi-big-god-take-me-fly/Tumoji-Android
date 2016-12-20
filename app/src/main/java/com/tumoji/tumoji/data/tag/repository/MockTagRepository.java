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
        tagModels.add(new TagModel().withTagName("熊本"));
        tagModels.add(new TagModel().withTagName("仓鼠"));
        tagModels.add(new TagModel().withTagName("咸鱼"));
        tagModels.add(new TagModel().withTagName("皮卡丘"));
        tagModels.add(new TagModel().withTagName("单身狗"));
        tagModels.add(new TagModel().withTagName("搞事"));
    }

    @Override
    public void getTagsList(OnGetTagsListListener listener) {
        handler.postDelayed(() -> {
            listener.onSuccess(tagModels);
        }, 1000);
    }

    @Override
    public List<TagModel> getCachedTagsList() {
        return tagModels;
    }
}
