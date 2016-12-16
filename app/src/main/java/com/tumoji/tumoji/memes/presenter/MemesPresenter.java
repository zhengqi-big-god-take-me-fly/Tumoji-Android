package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.account.repository.IAccountRepository;
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
    private IAccountRepository mAccountRepository;
    private IMemeRepository mMemeRepository;
    private ITagRepository mTagRepository;
    private MemesContract.View mView;

    public MemesPresenter(IAccountRepository accountRepository, IMemeRepository memeRepository, ITagRepository tagRepository, MemesContract.View view) {
        mAccountRepository = accountRepository;
        mMemeRepository = memeRepository;
        mTagRepository = tagRepository;
        mView = view;
    }

    @Override
    public void init() {
        mView.refreshTagsList(mTagRepository.getCachedTagsList());
        mTagRepository.getTagsList(new ITagRepository.OnGetTagsListListener() {
            @Override
            public void onSuccess(List<TagModel> tagModels) {
                mView.refreshTagsList(tagModels);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Handle error
            }
        });
    }

    @Override
    public void viewResume() {
        mView.refreshUserInfo(mAccountRepository.getSignedInAccount());
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
        if (mAccountRepository.hasSignedInAccount()) {
            mView.gotoMemeUploadPage();
        } else {
            mView.gotoSignInSignUpPage();
        }
    }

    @Override
    public void requestOpenUserProfilePage() {
        if (mAccountRepository.hasSignedInAccount()) {
            mView.gotoProfilePage();
        } else {
            mView.gotoSignInSignUpPage();
        }
    }

    @Override
    public void changeTag(TagModel tagModel) {
        mMemeRepository.getPopularMemesList(0, tagModel, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshPopularMemesList(memeModels, 0);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Handle error
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
        mMemeRepository.getNewMemesList(0, tagModel, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshNewMemesList(memeModels, 0);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO: Handle error
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }
}
