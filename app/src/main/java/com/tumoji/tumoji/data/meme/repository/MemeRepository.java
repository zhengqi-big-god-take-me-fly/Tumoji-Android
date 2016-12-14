package com.tumoji.tumoji.data.meme.repository;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.List;

/**
 * Created by souler on 16-12-14.
 */
public class MemeRepository implements IMemeRepository {
    /**
     * Like some meme
     *
     * @param memeModel The meme model to be liked
     * @param listener  Result callback
     */
    @Override
    public void likeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {

    }

    /**
     * Un-like some meme
     *
     * @param memeModel The meme model to be un-liked
     * @param listener  Result callback
     */
    @Override
    public void unlikeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {

    }

    /**
     * Report some meme
     *
     * @param memeModel The meme model to be reported
     * @param reason    The reason of this report
     * @param listener  Result callback
     */
    @Override
    public void reportMeme(MemeModel memeModel, String reason, OnReportMemeListener listener) {

    }

    /**
     * Get popular memes list from API server
     * NOTE: You should cache the gotten popular memes list to local storage (SQLite, etc.) for the
     * further calls of {@link #getCachedPopularMemesList() getCachedPopularMemesList} method.
     * However, you should only cache them when tag is not specified, because it's impossible to
     * cache memes for every tag.
     *
     * @param offset   The offset of the result list
     * @param tagModel The tag model specifying tag of memes, or null if no tag is specified
     * @param listener Result callback
     */
    @Override
    public void getPopularMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener) {

    }

    /**
     * Get new memes list from API server
     * NOTE: You should cache the gotten new memes list to local storage (SQLite, etc.) for the
     * further calls of {@link #getCachedNewMemesList()} getCachedNewMemesList} method.
     * However, you should only cache them when tag is not specified, because it's impossible to
     * cache memes for every tag.
     *
     * @param offset   The offset of the result list
     * @param tagModel The tag model specifying tag of memes, or null if no tag is specified
     * @param listener Result callback
     */
    @Override
    public void getNewMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener) {

    }

    /**
     * Get cached popular memes list from local storage
     *
     * @return Cached popular memes list
     */
    @Override
    public List<MemeModel> getCachedPopularMemesList() {
        return null;
    }

    /**
     * Get cached new memes list from local storage
     *
     * @return Cached new memes list
     */
    @Override
    public List<MemeModel> getCachedNewMemesList() {
        return null;
    }
}
