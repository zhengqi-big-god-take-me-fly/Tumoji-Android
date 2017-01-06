package com.tumoji.tumoji.memes.presenter;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.ITagRepository;
import com.tumoji.tumoji.memes.contract.MemeUploadContract;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/17/16
 */

public class MemeUploadPresenter implements MemeUploadContract.Presenter {
    private IMemeRepository mMemeRepository;
    private ITagRepository mTagRepository;
    private MemeUploadContract.View mView;

    public MemeUploadPresenter(IMemeRepository memeRepository, ITagRepository tagRepository, MemeUploadContract.View view) {
        this.mMemeRepository = memeRepository;
        this.mTagRepository = tagRepository;
        mView = view;
    }

    @Override
    public void init() {
        mTagRepository.getTagsList().subscribe(tagModels -> {
            mView.refreshTagsList(tagModels);
        }, throwable -> {
            // TODO
            throw new UnsupportedOperationException("Method not implemented");
        });
    }

    @Override
    public void requestUpload(Uri memePath, @NonNull String memeTitle, List<TagModel> tagModels) {
        if (memePath == null) {
            mView.failUploadWithNoMemeError();
        } else if (memeTitle.isEmpty()) {
            mView.failUploadWithNoTitleError();
        } else {
            mView.finishUploadAndClose();
        }
    }

    @Override
    public void requestStopUpload() {
        mView.stopUploadAndClose();
    }
}
