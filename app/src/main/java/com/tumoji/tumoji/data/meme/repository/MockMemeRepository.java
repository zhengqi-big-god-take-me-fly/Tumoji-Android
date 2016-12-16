package com.tumoji.tumoji.data.meme.repository;

import android.os.Handler;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/15/16
 */

public class MockMemeRepository implements IMemeRepository {
    private static IMemeRepository sInstance;

    private Handler mHandler;
    private ArrayList<MemeModel> mPopularMemeModels = new ArrayList<>();
    private ArrayList<MemeModel> mNewMemeModels = new ArrayList<>();

    public static IMemeRepository getInstance() {
        if (sInstance == null) {
            sInstance = new MockMemeRepository();
        }
        return sInstance;
    }

    private MockMemeRepository() {
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mPopularMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());
        mNewMemeModels.add(new MemeModel());

        mHandler = new Handler();
    }

    @Override
    public void likeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void unlikeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void reportMeme(MemeModel memeModel, String reason, OnReportMemeListener listener) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void getPopularMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener) {
        mHandler.postDelayed(() -> {
            // TODO: Change to real image
            for (int i = 0; i < 12; ++i) {
                mPopularMemeModels.add(new MemeModel().withImageUrl("http://google.com"));
            }
            listener.onSuccess(mPopularMemeModels);
        }, 5 * 1000);
    }

    @Override
    public void getNewMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener) {
        mHandler.postDelayed(() -> {
            // TODO: Change to real image
            for (int i = 0; i < 12; ++i) {
                mNewMemeModels.add(new MemeModel().withImageUrl("http://google.com"));
            }
            listener.onSuccess(mNewMemeModels);
        }, 5 * 1000);
    }

    @Override
    public List<MemeModel> getCachedPopularMemesList() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<MemeModel> getCachedNewMemesList() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
