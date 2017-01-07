package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.auth.repository.IAuthRepository;
import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.ITagRepository;
import com.tumoji.tumoji.data.user.repository.IUserRepository;
import com.tumoji.tumoji.memes.contract.MemesContract;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

public class MemesPresenter implements MemesContract.Presenter {
    private static final int PAGE_MEME_COUNT = 30;

    private IAuthRepository mAuthRepository;
    private IUserRepository mUserRepository;
    private IMemeRepository mMemeRepository;
    private ITagRepository mTagRepository;
    private MemesContract.View mView;

    public MemesPresenter(IAuthRepository authRepository, IUserRepository userRepository, IMemeRepository memeRepository, ITagRepository tagRepository, MemesContract.View view) {
        mAuthRepository = authRepository;
        mUserRepository = userRepository;
        mMemeRepository = memeRepository;
        mTagRepository = tagRepository;
        mView = view;
    }

    @Override
    public void init() {
        mTagRepository.getTagsList().subscribe(tagModels -> {
            if (tagModels.size() > 8) {
                tagModels = tagModels.subList(0, 8);
            }
            mView.refreshTagsList(tagModels);
        }, throwable -> {
            throwable.printStackTrace();
            mView.showUnexpectedError(throwable.getMessage());
        });
        mMemeRepository.getMemesList(0, PAGE_MEME_COUNT, null, IMemeRepository.ORDER_MOST_POPULAR).subscribe(memeModels -> {
            mView.refreshPopularMemesList(memeModels, 0);
        }, throwable -> {
            throwable.printStackTrace();
            mView.showUnexpectedError(throwable.getMessage());
        });
        mMemeRepository.getMemesList(0, PAGE_MEME_COUNT, null, IMemeRepository.ORDER_LATEST).subscribe(memeModels -> {
            mView.refreshNewMemesList(memeModels, 0);
        }, throwable -> {
            throwable.printStackTrace();
            mView.showUnexpectedError(throwable.getMessage());
        });
    }

    @Override
    public void viewResume() {
        if (mAuthRepository.isSignedIn()) {
            String userId = mAuthRepository.getLocalAuth().getUserId();
            // Refresh with local cache
            mUserRepository.getUser(userId).subscribe(userModel -> {
                mView.refreshUserInfo(userModel);
            }, throwable -> {
                throwable.printStackTrace();
                mView.showUnexpectedError(throwable.getMessage());
            });
            // Get latest profile
            mUserRepository.updateUser(userId).subscribe(userModel -> {
                mView.refreshUserInfo(userModel);
            }, throwable -> {
                throwable.printStackTrace();
                mView.showUnexpectedError(throwable.getMessage());
            });
        } else {
            mView.refreshUserInfo(null);
        }
    }

    @Override
    public void updatePopularMemesList(int offset) {
        mMemeRepository.getMemesList(offset, PAGE_MEME_COUNT, null, IMemeRepository.ORDER_MOST_POPULAR).subscribe(memeModels -> {
            mView.refreshPopularMemesList(memeModels, offset);
        });
    }

    @Override
    public void updatePopularMemesListOfTag(int offset, TagModel tagModel) {
        mMemeRepository.getMemesList(offset, PAGE_MEME_COUNT, tagModel, IMemeRepository.ORDER_MOST_POPULAR).subscribe(memeModels -> {
            mView.refreshPopularMemesList(memeModels, offset);
        });
    }

    @Override
    public void updateNewMemesList(int offset) {
        mMemeRepository.getMemesList(offset, PAGE_MEME_COUNT, null, IMemeRepository.ORDER_LATEST).subscribe(memeModels -> {
            mView.refreshNewMemesList(memeModels, offset);
        });
    }

    @Override
    public void updateNewMemesListOfTag(int offset, TagModel tagModel) {
        mMemeRepository.getMemesList(offset, PAGE_MEME_COUNT, tagModel, IMemeRepository.ORDER_LATEST).subscribe(memeModels -> {
            mView.refreshNewMemesList(memeModels, offset);
        });
    }

    @Override
    public void requestUploadingMeme() {
        if (mAuthRepository.isSignedIn()) {
            mView.gotoMemeUploadPage();
        } else {
            mView.gotoSignInSignUpPage();
        }
    }

    @Override
    public void requestOpenUserProfilePage() {
        if (mAuthRepository.isSignedIn()) {
            mView.gotoProfilePage();
        } else {
            mView.gotoSignInSignUpPage();
        }
    }

    @Override
    public void changeTag(TagModel tagModel) {
        if (tagModel == null) {
            updatePopularMemesList(0);
            updateNewMemesList(0);
        } else {
            updatePopularMemesListOfTag(0, tagModel);
            updateNewMemesListOfTag(0, tagModel);
        }
    }
}
