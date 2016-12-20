package com.tumoji.tumoji.data.meme.repository;

import com.tumoji.tumoji.common.DemoMemeStore;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/15/16
 */

public class MockMemeRepository implements IMemeRepository {
    private static IMemeRepository sInstance;


    private ArrayList<MemeModel> mAllMemes = new ArrayList<>();
    private ArrayList<MemeModel> mXiongbenMemes = new ArrayList<>();

    public static IMemeRepository getInstance() {
        if (sInstance == null) {
            sInstance = new MockMemeRepository();
        }
        return sInstance;
    }

    private MockMemeRepository() {
        mAllMemes = DemoMemeStore.getAllMemes();
        mXiongbenMemes = DemoMemeStore.getXiongbenMemes();
    }

    @Override
    public void likeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {
        listener.onSuccess(memeModel.withLiked(true));
    }

    @Override
    public void unlikeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {
        listener.onSuccess(memeModel.withLiked(false));
    }

    @Override
    public void reportMeme(MemeModel memeModel, String reason, OnReportMemeListener listener) {
        listener.onSuccess(memeModel.withReported(true));
    }

    @Override
    public void getPopularMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener) {
        ArrayList<MemeModel> memes = new ArrayList<>();
        if (tagModel != null) {
            memes.addAll(mXiongbenMemes);
        } else {
            memes.addAll(mAllMemes);
        }
        MemeModel[] memesArray = toArray(memes);
        Arrays.sort(memesArray, (o1, o2) -> o2.getLikeCount() - o1.getLikeCount());
        ArrayList<MemeModel> result = new ArrayList<>();
        Collections.addAll(result, memesArray);
        if (offset >= result.size()) {
            listener.onSuccess(new ArrayList<>());
            return;
        }
        listener.onSuccess(result.subList(offset, offset + 15 > result.size() ? result.size() : offset + 15));
    }

    @Override
    public void getNewMemesList(int offset, TagModel tagModel, OnGetMemesListListener listener) {
        ArrayList<MemeModel> memes = new ArrayList<>();
        if (tagModel != null) {
            memes.addAll(mXiongbenMemes);
        } else {
            memes.addAll(mAllMemes);
        }
        if (offset >= memes.size()) {
            listener.onSuccess(new ArrayList<>());
            return;
        }
        listener.onSuccess(memes.subList(offset, offset + 15 > memes.size() ? memes.size() : offset + 15));
    }

    private MemeModel[] toArray(ArrayList<MemeModel> memeModels) {
        MemeModel[] memes = new MemeModel[memeModels.size()];
        for (int i = 0; i < memeModels.size(); ++i) {
            memes[i] = memeModels.get(i);
        }
        return memes;
    }

    @Override
    public List<MemeModel> getCachedPopularMemesList() {
        ArrayList<MemeModel> memes = mAllMemes;
        MemeModel[] memesArray = toArray(memes);
        Arrays.sort(memesArray, (o1, o2) -> o2.getLikeCount() - o1.getLikeCount());
        ArrayList<MemeModel> result = new ArrayList<>();
        Collections.addAll(result, memesArray);
        return result.subList(0, result.size() >= 15 ? 15 : result.size());
    }

    @Override
    public List<MemeModel> getCachedNewMemesList() {
        return mAllMemes.subList(0, mAllMemes.size() >= 15 ? 15 : mAllMemes.size());
    }

    @Override
    public void getMeme(String memeId, OnGetResultListener<MemeModel> listener) {
        for (MemeModel memeModel : mAllMemes) {
            if (memeModel.getMemeId().equals(memeId)) {
                listener.onSuccess(memeModel);
                return;
            }
        }
    }

    @Override
    public MemeModel getCachedMeme(String memeId) {
        for (MemeModel memeModel : mAllMemes) {
            if (memeModel.getMemeId().equals(memeId)) {
                return memeModel;
            }
        }
        return null;
    }
}
