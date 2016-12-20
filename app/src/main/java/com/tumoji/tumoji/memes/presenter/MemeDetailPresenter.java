package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.meme.repository.IMemeRepository;
import com.tumoji.tumoji.memes.contract.MemeDetailContract;

/**
 * Author: perqin
 * Date  : 12/17/16
 */

public class MemeDetailPresenter implements MemeDetailContract.Presenter {
    private IMemeRepository mMemeRepository;
    private MemeDetailContract.View mView;

    public MemeDetailPresenter(IMemeRepository memeRepository, MemeDetailContract.View view) {
        this.mMemeRepository = memeRepository;
        this.mView = view;
    }

    @Override
    public void init(String memeId) {
        mView.refreshMemeDetail(mMemeRepository.getCachedMeme(memeId));
        mMemeRepository.getMeme(memeId, new IMemeRepository.OnGetResultListener<MemeModel>() {
            @Override
            public void onSuccess(MemeModel result) {
                mView.refreshMemeDetail(result);
            }

            @Override
            public void onFailure(int error, String msg) {
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void likeMeme(MemeModel memeModel) {
        mMemeRepository.likeMeme(memeModel, new IMemeRepository.OnLikeUnlikeMemeListener() {
            @Override
            public void onSuccess(MemeModel memeModel) {
                mView.refreshMemeDetail(memeModel);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void unlikeMeme(MemeModel memeModel) {
        mMemeRepository.unlikeMeme(memeModel, new IMemeRepository.OnLikeUnlikeMemeListener() {
            @Override
            public void onSuccess(MemeModel memeModel) {
                mView.refreshMemeDetail(memeModel);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void reportMeme(MemeModel memeModel, String reason) {
        mMemeRepository.reportMeme(memeModel, reason, new IMemeRepository.OnReportMemeListener() {
            @Override
            public void onSuccess(MemeModel memeModel) {
                mView.refreshMemeDetail(memeModel);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }

    @Override
    public void saveMeme(MemeModel mMemeModel) {
        mMemeRepository.saveMeme(mMemeModel, new OnGetResultListener<MemeModel>() {
            @Override
            public void onSuccess(MemeModel result) {
                mView.refreshMemeDetail(result);
            }

            @Override
            public void onFailure(int error, String msg) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }
}
