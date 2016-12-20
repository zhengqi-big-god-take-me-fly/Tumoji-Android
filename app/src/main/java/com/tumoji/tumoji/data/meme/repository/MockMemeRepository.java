package com.tumoji.tumoji.data.meme.repository;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;

import com.tumoji.tumoji.common.DemoMemeStore;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: perqin
 * Date  : 12/15/16
 */

public class MockMemeRepository implements IMemeRepository {
    private static IMemeRepository sInstance;

    private File mMemeDownloadDirectory;
    private ArrayList<MemeModel> mAllMemes = new ArrayList<>();
    private ArrayList<MemeModel> mXiongbenMemes = new ArrayList<>();
    private Context mAppContext;

    public static IMemeRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MockMemeRepository(context);
        }
        return sInstance;
    }

    private MockMemeRepository(Context context) {
        mAppContext = context.getApplicationContext();
        mMemeDownloadDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Tumoji");
        mMemeDownloadDirectory.mkdirs();
        mAllMemes = DemoMemeStore.getAllMemes();
        mXiongbenMemes = DemoMemeStore.getXiongbenMemes();
    }

    @Override
    public void likeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {
        listener.onSuccess(memeModel.withLiked(true).withLikeCount(memeModel.getLikeCount() + 1));
    }

    @Override
    public void unlikeMeme(MemeModel memeModel, OnLikeUnlikeMemeListener listener) {
        listener.onSuccess(memeModel.withLiked(false).withLikeCount(memeModel.getLikeCount() - 1));
    }

    @Override
    public void reportMeme(MemeModel memeModel, String reason, OnReportMemeListener listener) {
        listener.onSuccess(memeModel.withReported(true).withReportCount(memeModel.getReportCount() + 1));
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

    @Override
    public void saveMeme(MemeModel memeModel, com.tumoji.tumoji.common.OnGetResultListener<MemeModel> listener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(memeModel.getImageUrl()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO
                throw new UnsupportedOperationException("Method not implemented");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String filename = getFilenameFromUrl(memeModel.getImageUrl());
                        File memeFile = new File(mMemeDownloadDirectory, filename);
                        FileOutputStream fileOutputStream = new FileOutputStream(memeFile);
                        InputStream inputStream = response.body().byteStream();
                        try {
                            byte[] buffer = new byte[4096];
                            int read;
                            while ((read = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, read);
                            }
                            fileOutputStream.flush();
                            MediaScannerConnection.scanFile(mAppContext, new String[]{ memeFile.getAbsolutePath() }, null, null);
                        } finally {
                            fileOutputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private String getFilenameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
