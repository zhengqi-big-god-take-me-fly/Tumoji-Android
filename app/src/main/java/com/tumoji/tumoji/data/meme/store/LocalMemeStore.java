package com.tumoji.tumoji.data.meme.store;

import android.net.Uri;

import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.storage.sqlite.MemeDatabase;

import java.io.File;

/**
 * Author: perqin
 * Date  : 1/7/17
 */

public class LocalMemeStore {
    private final MemeDatabase mDb;

    public LocalMemeStore() {
        mDb = MemeDatabase.getInstance();
    }

    public MemeModel fulfillDownloaded(File parentDir, MemeModel memeModel) {
        String filename = mDb.getMemeFileNameById(memeModel.getMemeId());
        if (filename == null) {
            memeModel.setDownloaded(false);
            memeModel.setMemeUri(Uri.parse(memeModel.getImageUrl()));
        } else {
            memeModel.setDownloaded(true);
            memeModel.setMemeUri(Uri.fromFile(new File(parentDir, filename)));
        }
        return memeModel;
    }
}
