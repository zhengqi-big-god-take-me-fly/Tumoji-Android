package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.tag.repository.ITagRepository;
import com.tumoji.tumoji.memes.contract.MoreTagsContract;

/**
 * Author: perqin
 * Date  : 12/16/16
 */

public class MoreTagsPresenter implements MoreTagsContract.Presenter {
    private ITagRepository mTagRepository;
    private MoreTagsContract.View mView;

    public MoreTagsPresenter(ITagRepository tagRepository, MoreTagsContract.View view) {
        mTagRepository = tagRepository;
        mView = view;
    }

    @Override
    public void init() {
        mView.startLoading();
        mTagRepository.getTagsList().subscribe(tagModels -> {
            mView.finishLoading(tagModels);
        }, throwable -> {
            throwable.printStackTrace();
            mView.showUnexpectedError(throwable.getMessage());
        });
    }
}
