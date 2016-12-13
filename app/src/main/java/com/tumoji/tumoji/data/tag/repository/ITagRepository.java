package com.tumoji.tumoji.data.tag.repository;

import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/13/16
 *
 * Implement this interface to provide network and local access to tags.
 * The implementing class is recommended to be a Singleton.
 *
 * NOTE: Communicate with outside this class using Model.
 */

public interface ITagRepository {
    /**
     * Get latest tags list from API server.
     * NOTE: You should cache the gotten tags list to local storage (SQLite, etc.) for the further
     * calls of {@link #getCachedTagsList() getCachedTagsList} method.
     * @param listener Result callback
     */
    void getTagsList(OnGetTagsListListener listener);

    /**
     * Get cached tags list from local storage
     * @return Cached tags list
     */
    List<TagModel> getCachedTagsList();

    /**
     * Listener on tags list gotten.
     */
    interface OnGetTagsListListener {
        /**
         * Get tags list successfully
         * @param tagModels Tags list gotten
         */
        void onSuccess(List<TagModel> tagModels);

        /**
         * Fail to get tags list
         * @param error Error code
         * @param msg Error message
         */
        void onFailure(int error, String msg);
    }
}
