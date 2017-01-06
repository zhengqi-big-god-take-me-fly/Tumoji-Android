package com.tumoji.tumoji.data.meme.repository;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.io.File;
import java.util.List;

import rx.Observable;

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
    int ORDER_MOST_POPULAR = 0;
    int ORDER_LATEST = 1;

    /**
     * Like or un-like specific meme and get updated meme data
     * NOTE: If the meme is downloaded, give local file URI instead of URL in MemeModel.
     * @param token Access token
     * @param memeId The meme ID to be liked or un-liked
     * @param like like or un-like
     * @return Observable which emits updated meme data
     */
    Observable<MemeModel> likeMeme(String token, String memeId, boolean like);

    /**
     * Report specific meme with reason
     * NOTE: If the meme is downloaded, give local file URI instead of URL in MemeModel.
     * @param token Access token
     * @param memeId The meme ID to be reported
     * @param reason Report reason
     * @return Observable which emits updated meme data
     */
    Observable<MemeModel> reportMeme(String token, String memeId, String reason);

    /**
     * Get popular memes list from API server
     * NOTE: If the meme is downloaded, give local file URI instead of URL in MemeModel.
     * @param offset The offset of the result list
     * @param count The size of the result list
     * @param tagModel The tag filter of the result list, or null if no tag is specified
     * @param order The order of the result list. Must be {@link #ORDER_MOST_POPULAR} or {@link #ORDER_LATEST}
     * @return Observable which emits a list of MemeModel
     */
    Observable<List<MemeModel>> getMemesList(int offset, int count, TagModel tagModel, int order);

    /**
     * Get meme data from API server
     * NOTE: If the meme is downloaded, give local file URI instead of URL in MemeModel.
     * @param memeId The ID of the meme
     * @return Observable which emits the MemeModel
     */
    Observable<MemeModel> getMeme(String memeId);

    /**
     * Download specific meme and save it to specific directory
     * NOTE: If the meme is downloaded, give local file URI instead of URL in MemeModel.
     * @param memeId The ID of the meme to be downloaded
     * @param destDir The destination directory of the meme
     * @return Observable which emits the downloaded MemeModel
     */
    Observable<MemeModel> downloadMeme(String memeId, File destDir);

    /**
     * Upload new meme
     * @param memeModel The detail data of the meme
     * @param memeFile The file of the meme image
     * @return Observable which emits the MemeModel uploaded.
     */
    Observable<MemeModel> uploadMeme(MemeModel memeModel, File memeFile);

    /**
     * @deprecated Use {@link #likeMeme(String, String, boolean)} instead.
     * Like some meme
     * @param memeModel The meme model to be liked
     * @param listener Result callback
     */
    void likeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener);

    /**
     * @deprecated Use {@link #likeMeme(String, String, boolean)} instead
     * Un-like some meme
     * @param memeModel The meme model to be un-liked
     * @param listener Result callback
     */
    void unlikeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener);

    /**
     * @deprecated Use {@link #reportMeme(String, String, String)} instead
     * Report some meme
     * @param memeModel The meme model to be reported
     * @param reason The reason of this report
     * @param listener Result callback
     */
    void reportMeme(MemeModel memeModel, String reason, OnReportMemeListener listener);

    /**
     * @deprecated Use {@link #getMemesList(int, int, TagModel, int)} instead
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
     * @deprecated Use {@link #getMemesList(int, int, TagModel, int)} instead
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
     * @deprecated This feature is not supported further more.
     * Get cached popular memes list from local storage
     * @return Cached popular memes list
     */
    List<MemeModel> getCachedPopularMemesList();

    /**
     * @deprecated This feature is not supported further more.
     * Get cached new memes list from local storage
     * @return Cached new memes list
     */
    List<MemeModel> getCachedNewMemesList();

    /**
     * @deprecated Use {@link #getMeme(String)} instead.
     * Get meme data from API server
     * @param memeId The meme ID of the meme to get
     * @param listener Callback
     */
    void getMeme(String memeId, OnGetResultListener<MemeModel> listener);

    /**
     * @deprecated This feature is not supported further more.
     * Get cached meme data from local storage
     * @param memeId The ID of the meme
     * @return The meme data
     */
    MemeModel getCachedMeme(String memeId);

    /**
     * @deprecated Use {@link #downloadMeme(String, File)} instead
     * Save meme file to local storage
     * @param memeModel The meme data object
     * @param listener Callback
     */
    void saveMeme(MemeModel memeModel, com.tumoji.tumoji.common.OnGetResultListener<MemeModel> listener);

    @Deprecated
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

    @Deprecated
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

    @Deprecated
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

    @Deprecated
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
