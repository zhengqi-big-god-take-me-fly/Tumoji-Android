package com.tumoji.tumoji.data.meme.repository;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.List;

/**
 * Author   : perqin
 * Date     : 16-12-5
 *
 * Implement this interface to provide network and local access to memes.
 * The implementing class is recommended to be a Singleton.
 *
 * NOTE: Communicate with outside this class using Model.
 */

public interface IMemeRepository {
    /**
     * Like some meme
     * @param memeModel The meme model to be liked
     * @param listener Result callback
     */
    void likeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener);

    /**
     * Un-like some meme
     * @param memeModel The meme model to be un-liked
     * @param listener Result callback
     */
    void unlikeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener);

    /**
     * Report some meme
     * @param memeModel The meme model to be reported
     * @param reason The reason of this report
     * @param listener Result callback
     */
    void reportMeme(MemeModel memeModel, String reason, OnReportMemeListener listener);

    /**
     * Get popular memes list from API server
     * NOTE: You should cache the gotten popular memes list to local storage (SQLite, etc.) for the
     * further calls of {@link #getCachedPopularMemesList() getCachedPopularMemesList} method.
     * However, you should only cache them when tag is not specified, because it's impossible to
     * cache memes for every tag.
     * @param offset The offset of the result list
     * @param tagModel The tag model specifying tag of memes, or null if no tag is specified
     * @param listener Result callback
     */
    void getPopularMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener);

    /**
     * Get new memes list from API server
     * NOTE: You should cache the gotten new memes list to local storage (SQLite, etc.) for the
     * further calls of {@link #getCachedNewMemesList()} getCachedNewMemesList} method.
     * However, you should only cache them when tag is not specified, because it's impossible to
     * cache memes for every tag.
     * @param offset The offset of the result list
     * @param tagModel The tag model specifying tag of memes, or null if no tag is specified
     * @param listener Result callback
     */
    void getNewMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener);

    /**
     * Get cached popular memes list from local storage
     * @return Cached popular memes list
     */
    List<MemeModel> getCachedPopularMemesList();

    /**
     * Get cached new memes list from local storage
     * @return Cached new memes list
     */
    List<MemeModel> getCachedNewMemesList();

    /**
     * Get meme data from API server
     * @param memeId The meme ID of the meme to get
     * @param listener Callback
     */
    void getMeme(String memeId, OnGetResultListener<MemeModel> listener);

    /**
     * Get cached meme data from local storage
     * @param memeId The ID of the meme
     * @return The meme data
     */
    MemeModel getCachedMeme(String memeId);

    interface OnLikeUnlikeMemeListener {
        /**
         * Like or un-like a meme successfully
         * @param memeModel The newest meme model
         */
        void onSuccess(MemeModel memeModel);

        /**
         * Fail to like or un-like a meme
         * @param error Error code
         * @param msg Error message
         */
        void onFailure(int error, String msg);
    }

    interface OnReportMemeListener {
        /**
         * Report a meme successfully
         * @param memeModel The newest meme model
         */
        void onSuccess(MemeModel memeModel);

        /**
         * Fail to report a meme
         * @param error Error code
         * @param msg Error message
         */
        void onFailure(int error, String msg);
    }

    interface OnGetResultListener<T> {
        /**
         * Successfully get things
         * @param result The things to get
         */
        void onSuccess(T result);

        /**
         * Fail to get things
         * @param error Error code
         * @param msg Error message
         */
        void onFailure(int error, String msg);
    }

    interface OnGetMemesListListener {
        /**
         * Get memes list successfully
         * @param memeModels The list of memes gotten
         */
        void onSuccess(List<MemeModel> memeModels);

        /**
         * Fail to get memes list
         * @param error Error code
         * @param msg Error message
         */
        void onFailure(int error, String msg);
    }
}
