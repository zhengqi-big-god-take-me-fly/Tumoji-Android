package com.tumoji.tumoji.data.meme.repository;

import android.os.Handler;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        for (int i = 0; i < 12; ++i) {
            mPopularMemeModels.add(generateRandomMeme());
        }
        for (int i = 0; i < 10; ++i) {
            mNewMemeModels.add(generateRandomMeme());
        }

        mHandler = new Handler();
    }

    private MemeModel generateRandomMeme() {
        String[] titles = new String[] {
                "Girl should be WU",
                "Fear",
                "The other one don't want to talk to you and throw you a dog"
        };
        String[] urls = new String[] {
                "http://qcloud.perqin.com/girl-wu.jpg",
                "http://qcloud.perqin.com/fear.png",
                "http://qcloud.perqin.com/throw-dog.gif"
        };
        int index = new Random().nextInt(3);
        return new MemeModel().withTitle(titles[index]).withImageUrl(urls[index]);
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
        if (offset == 0) {
            mHandler.postDelayed(() -> {
                mPopularMemeModels.add(0, generateRandomMeme());
                mPopularMemeModels.add(0, generateRandomMeme());
                mPopularMemeModels.add(0, generateRandomMeme());
                mPopularMemeModels.add(0, generateRandomMeme());
                listener.onSuccess(mPopularMemeModels.subList(0, mPopularMemeModels.size() < 20 ? mPopularMemeModels.size() : 20));
            }, 3000);
        } else {
            mHandler.postDelayed(() -> {
                for (int i = 0; i < 15; ++i) {
                    mPopularMemeModels.add(generateRandomMeme());
                }
                listener.onSuccess(mPopularMemeModels.subList(offset, offset + 15));
            }, 6000);
        }
    }

    @Override
    public void getNewMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener) {
        if (offset == 0) {
            mHandler.postDelayed(() -> {
                mNewMemeModels.add(0, generateRandomMeme());
                listener.onSuccess(mNewMemeModels.subList(0, 10));
            }, 3000);
        } else {
            mHandler.postDelayed(() -> {
                for (int i = 0; i < 9; ++i) {
                    mNewMemeModels.add(generateRandomMeme());
                }
                listener.onSuccess(mNewMemeModels.subList(offset, offset + 10));
            }, 6000);
        }
    }

    @Override
    public List<MemeModel> getCachedPopularMemesList() {
        return mPopularMemeModels.subList(0, 10);
    }

    @Override
    public List<MemeModel> getCachedNewMemesList() {
        return mNewMemeModels.subList(0, 10);
    }

    @Override
    public void getMeme(String memeId, OnGetResultListener<MemeModel> listener) {
        mHandler.postDelayed(() -> {
            listener.onSuccess(new MemeModel().withTitle("New title"));
        }, 1000);
    }

    @Override
    public MemeModel getCachedMeme(String memeId) {
        return new MemeModel().withMemeId(memeId).withTitle("Original meme title");
    }
}
