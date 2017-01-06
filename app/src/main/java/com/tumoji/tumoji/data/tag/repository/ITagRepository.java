package com.tumoji.tumoji.data.tag.repository;

import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.List;

import rx.Observable;

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
     * Get latest tags list from API server
     * // NOTE: You should update local cache in this method.
     * @return Observable which emits a list of TagModel
     */
    Observable<List<TagModel>> getTagsList();

    /**
     * Get cached tags list from local storage
     * @return Cached tags list
     */
    List<TagModel> getCachedTagsList();

    /**
     * Get tags list of a meme from API server
     * @param memeId The id of the meme
     * @return Observable which emits a list of TagModel for specific meme
     */
    Observable<List<TagModel>> getTagsListOfMeme(String memeId);

    /**
     * @deprecated Use {@link #getTagsList()} instead.
     * Get latest tags list from API server.
     * NOTE: You should cache the gotten tags list to local storage (SQLite, etc.) for the further
     * calls of {@link #getCachedTagsList() getCachedTagsList} method.
     * @param listener Result callback
     */
    void getTagsList(OnGetTagsListListener listener);

    Observable<Void> addTagsForMeme(String token, String memeId, List<TagModel> tagModels);

    /**
     * @deprecated
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
