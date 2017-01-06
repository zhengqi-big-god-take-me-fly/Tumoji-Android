package com.tumoji.tumoji.memes.presenter;

import android.support.annotation.NonNull;

import com.tumoji.tumoji.data.auth.repository.IAuthRepository;
import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.ITagRepository;
import com.tumoji.tumoji.memes.contract.MemeUploadContract;

import java.io.File;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/17/16
 */

public class MemeUploadPresenter implements MemeUploadContract.Presenter {
    private IAuthRepository mAuthRepository;
    private IMemeRepository mMemeRepository;
    private ITagRepository mTagRepository;
    private MemeUploadContract.View mView;

    public MemeUploadPresenter(IAuthRepository authRepository, IMemeRepository memeRepository, ITagRepository tagRepository, MemeUploadContract.View view) {
        this.mAuthRepository = authRepository;
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
    public void requestUpload(File memeFile, @NonNull String memeTitle, List<TagModel> tagModels) {
        if (memeFile == null) {
            mView.failUploadWithNoMemeError();
        } else if (memeTitle.isEmpty()) {
            mView.failUploadWithNoTitleError();
        } else {
            // Seq: Upload meme -> add tags
            mMemeRepository.uploadMeme(mAuthRepository.getLocalAuth().getAccessToken(), memeTitle, memeFile)
                    .flatMap(memeModel -> mTagRepository.addTagsForMeme(memeModel.getMemeId(), tagModels))
                    .subscribe(aVoid -> {
                        mView.finishUploadAndClose();
                    }, throwable -> {
                        // TODO
                        throw new UnsupportedOperationException("Method not implemented");
                    });
        }
    }

    @Override
    public void requestStopUpload() {
        mView.stopUploadAndClose();
    }
}
