package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.memes.contract.MemesContract;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

public class MemesPresenter implements MemesContract.Presenter {
    @Override
    public void init() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void memeThumbnailItemClicked(MemeModel memeModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void likeMeme(MemeModel memeModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void reportMeme(MemeModel memeModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updatePopularMemesList(int offset) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updatePopularMemesListOfTag(int offset, TagModel tagModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updateNewMemesList(int offset) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updateNewMemesListOfTag(int offset, TagModel tagModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void requestUploadingMeme() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
