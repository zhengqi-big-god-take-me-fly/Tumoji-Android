package com.tumoji.tumoji.memes.presenter;

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
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void unlikeMeme(MemeModel memeModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void reportMeme(MemeModel memeModel, String reason) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
