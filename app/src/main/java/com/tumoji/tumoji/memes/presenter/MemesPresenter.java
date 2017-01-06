package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.auth.repository.IAuthRepository;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.ITagRepository;
import com.tumoji.tumoji.data.user.repository.IUserRepository;
import com.tumoji.tumoji.memes.contract.MemesContract;

import java.util.List;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

public class MemesPresenter implements MemesContract.Presenter {
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
        // Show cached content
//        mView.refreshTagsList(mTagRepository.getCachedTagsList());
        // Refresh latest content
//        mTagRepository.getTagsList(new ITagRepository.OnGetTagsListListener() {
//            @Override
//            public void onSuccess(List<TagModel> tagModels) {
//                mView.refreshTagsList(tagModels);
//            }
//
//            @Override
//            public void onFailure(int error, String msg) {
//                // TODO: Handle error
//            }
//        });
        mTagRepository.getTagsList().subscribe(tagModels -> {
            if (tagModels.size() > 8) {
                tagModels = tagModels.subList(0, 8);
            }
            mView.refreshTagsList(tagModels);
        }, throwable -> {
            // TODO
            throw new UnsupportedOperationException("Method not implemented");
        });
        mMemeRepository.getPopularMemesList(0, null, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshPopularMemesList(memeModels, 0);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
        mMemeRepository.getNewMemesList(0, null, new IMemeRepository.OnGetMemesListListener() {
            @Override
            public void onSuccess(List<MemeModel> memeModels) {
                mView.refreshNewMemesList(memeModels, 0);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }
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
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            });
            // Get latest profile
            mUserRepository.updateUser(userId).subscribe(userModel -> {
                mView.refreshUserInfo(userModel);
            }, throwable -> {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            });
        } else {
            mView.refreshUserInfo(null);
        }
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
