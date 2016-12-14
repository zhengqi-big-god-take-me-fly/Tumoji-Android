package com.tumoji.tumoji.data.tag.repository;

import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.List;

/**
 * Created by souler on 16-12-14.
 */
public class TagRepository implements ITagRepository {
    /**
     * Get latest tags list from API server.
     * NOTE: You should cache the gotten tags list to local storage (SQLite, etc.) for the further
     * calls of {@link #getCachedTagsList() getCachedTagsList} method.
     *
     * @param listener Result callback
     */
    @Override
    public void getTagsList(OnGetTagsListListener listener) {

    }

    /**
     * Get cached tags list from local storage
     *
     * @return Cached tags list
     */
    @Override
    public List<TagModel> getCachedTagsList() {
        return null;
    }
}
