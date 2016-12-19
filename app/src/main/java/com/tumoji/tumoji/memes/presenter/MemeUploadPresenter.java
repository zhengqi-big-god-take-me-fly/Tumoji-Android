package com.tumoji.tumoji.memes.presenter;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.memes.contract.MemeUploadContract;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/17/16
 */

public class MemeUploadPresenter implements MemeUploadContract.Presenter {
    private IMemeRepository mMemeRepository;
    private MemeUploadContract.View mView;

    public MemeUploadPresenter(IMemeRepository memeRepository, MemeUploadContract.View view) {
        this.mMemeRepository = memeRepository;
        mView = view;
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
