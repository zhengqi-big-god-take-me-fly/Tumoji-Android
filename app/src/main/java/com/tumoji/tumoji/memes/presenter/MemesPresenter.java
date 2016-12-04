package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.meme.bean.Meme;
import com.tumoji.tumoji.data.tag.bean.Tag;
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
    public void memeThumbnailItemClicked(Meme meme) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void likeMeme(Meme meme) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void reportMeme(Meme meme) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updatePopularMemesList(int offset) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updatePopularMemesListOfTag(int offset, Tag tag) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updateNewMemesList(int offset) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void updateNewMemesListOfTag(int offset, Tag tag) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void requestUploadingMeme() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
