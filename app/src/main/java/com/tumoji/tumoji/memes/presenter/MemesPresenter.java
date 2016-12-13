package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.ITagRepository;
import com.tumoji.tumoji.memes.contract.MemesContract;

import java.util.List;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

public class MemesPresenter implements MemesContract.Presenter {
    private IMemeRepository mMemeRepository;
    private ITagRepository mTagRepository;
    private MemesContract.View mView;

    public MemesPresenter(IMemeRepository memeRepository, ITagRepository tagRepository, MemesContract.View view) {
        mMemeRepository = memeRepository;
        mTagRepository = tagRepository;
        mView = view;
    }

    @Override
    public void init() {
        // TODO: Implement com.tumoji.tumoji.memes.presenter.MemesPresenter.init
    }

    @Override
    public void memeThumbnailItemClicked(MemeModel memeModel) {
        mView.showHdMeme(memeModel);
    }

    @Override
    public void likeMeme(MemeModel memeModel) {
        mMemeRepository.likeMeme(memeModel, new IMemeRepository.OnLikeUnlikeMemeListener() {
            @Override
            public void onSuccess(MemeModel newMemeModel) {
                mView.refreshHdMeme(newMemeModel);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void unlikeMeme(MemeModel memeModel) {
        mMemeRepository.unlikeMeme(memeModel, new IMemeRepository.OnLikeUnlikeMemeListener() {
            @Override
            public void onSuccess(MemeModel newMemeModel) {
                mView.refreshHdMeme(newMemeModel);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void reportMeme(MemeModel memeModel, String reason) {
        mMemeRepository.reportMeme(memeModel, reason, new IMemeRepository.OnReportMemeListener() {
            @Override
            public void onSuccess(MemeModel newMemeModel) {
                mView.refreshHdMeme(newMemeModel);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void updatePopularMemesList(final int offset) {
        mMemeRepository.getPopularMemesList(offset, null, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshPopularMemesList(memeModels, offset);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void updatePopularMemesListOfTag(final int offset, TagModel tagModel) {
        mMemeRepository.getPopularMemesList(offset, tagModel, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshPopularMemesList(memeModels, offset);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void updateNewMemesList(final int offset) {
        mMemeRepository.getNewMemesList(offset, null, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshNewMemesList(memeModels, offset);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void updateNewMemesListOfTag(final int offset, TagModel tagModel) {
        mMemeRepository.getNewMemesList(offset, tagModel, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshNewMemesList(memeModels, offset);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void updateTagsList() {
        mTagRepository.getTagsList(new ITagRepository.OnGetTagsListListener() {
            @Override
            public void onSuccess(List<TagModel> tagModels) {
                mView.refreshTagsList(tagModels);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Implement .onFailure
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void requestUploadingMeme() {
        // TODO: Implement com.tumoji.tumoji.memes.presenter.MemesPresenter.requestUploadingMeme
        throw new UnsupportedOperationException("Method not implemented");
    }
}