package com.tumoji.tumoji.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.provider.Telephony;


import com.tumoji.tumoji.data.meme.model.MemeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by souler on 16-12-14.
 */
public class MemeDatabase {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase readableMemeDatas;
    private SQLiteDatabase writableMemeDatas;
    public static MemeDatabase memeDatabase;

    public static MemeDatabase getInstance(Context context) {
        if (memeDatabase == null) memeDatabase = new MemeDatabase(new DBOpenHelper(context));
        return memeDatabase;
    }

    private MemeDatabase(DBOpenHelper openHelper) {
        super();
        dbOpenHelper = openHelper;
        writableMemeDatas = openHelper.getWritableDatabase();
        readableMemeDatas = openHelper.getReadableDatabase();
    }

    /**
     * Get all local Memes
     * @return a list of Mememodel
     */
    public ArrayList<MemeModel> getAllLocalMemes() {
        ArrayList<MemeModel> ls = new ArrayList<>();
        if (readableMemeDatas == null) return null;

        /**
         * Perform query
         */
        Cursor memeCursor = readableMemeDatas.rawQuery("select * from " + DBOpenHelper.MEME_TABLE , null);
        while (memeCursor.moveToNext()) {
            String memeID = memeCursor.getString(memeCursor.getColumnIndex(DBOpenHelper.MEME_ID));
            String memeTitle = memeCursor.getString(memeCursor.getColumnIndex(DBOpenHelper.MEME_TITLE));
            String memeAuthorID = memeCursor.getString(memeCursor.getColumnIndex(DBOpenHelper.MEME_AUTHORID));
            String memeURL = memeCursor.getString(memeCursor.getColumnIndex(DBOpenHelper.MEME_DOWNLOAD_URL));
            MemeModel tempModel = new MemeModel(memeID,memeTitle,memeAuthorID,memeURL);
            ls.add(tempModel);
        }
        memeCursor.close();
        return ls;

    }

    /**
     * Get meme by name
     * @param name
     * @return mememodel mmm
     */
    public MemeModel getMemeByName(String name) {
        if (readableMemeDatas == null) return null;
        MemeModel tempModel = null;

        /**
         * Perform query
         */
        Cursor cursor = readableMemeDatas.rawQuery("select * from " + DBOpenHelper.MEME_TABLE + " where " + DBOpenHelper.MEME_TITLE + "=" + name , null);
        if (cursor.moveToNext()) {
            String memeID = cursor.getString(cursor.getColumnIndex(DBOpenHelper.MEME_ID));
            String memeTitle = cursor.getString(cursor.getColumnIndex(DBOpenHelper.MEME_TITLE));
            String memeAuthorID = cursor.getString(cursor.getColumnIndex(DBOpenHelper.MEME_AUTHORID));
            String memeURL = cursor.getString(cursor.getColumnIndex(DBOpenHelper.MEME_DOWNLOAD_URL));
            tempModel = new MemeModel(memeID,memeTitle,memeAuthorID,memeURL);
            cursor.close();
        }
        return tempModel;
    }


    /**
     * meme Existence Checking function
     * @param id
     * @return boolean value of existency in name in database
     */
    private boolean checkMemeExistence(String id) {
        if (writableMemeDatas == null) return false;
        String queryStatement = " id=";
        Cursor cursor = writableMemeDatas.query(DBOpenHelper.MEME_TABLE , new String[] {
                DBOpenHelper.MEME_ID} , queryStatement , new String[] {
                id} , null , null , null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

    /**
     * Meme adding function
     * @param memeModel
     * @return success or not
     */
    public boolean addMeme(MemeModel memeModel) {
        if (writableMemeDatas == null || memeModel == null || checkMemeExistence(memeModel.getMemeId())) return false;

        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.MEME_ID , memeModel.getMemeId());
        cv.put(DBOpenHelper.MEME_TITLE , memeModel.getTitle());
        cv.put(DBOpenHelper.MEME_AUTHORID , memeModel.getAuthorId());
        cv.put(DBOpenHelper.MEME_DOWNLOAD_URL , memeModel.getImageUrl());

        writableMemeDatas.insert(DBOpenHelper.MEME_TABLE , null , cv);
        return true;
    }


    /**
     * Meme removal function
     * @param memeModel
     * @return success or not
     */
    public boolean removeMeme(MemeModel memeModel) {
        if (writableMemeDatas == null || memeModel == null || !checkMemeExistence(memeModel.getMemeId())) return false;

        writableMemeDatas.delete(DBOpenHelper.MEME_TABLE , DBOpenHelper.MEME_ID + "=?" , new String []{memeModel.getMemeId()});

        return true;

    }


    /**
     * meme updation Function
     * @param memeModel
     * @return success or not
     */
    public boolean updateMeme(MemeModel memeModel) {
        if (writableMemeDatas == null || memeModel == null || !checkMemeExistence(memeModel.getMemeId())) return false;

        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.MEME_ID , memeModel.getMemeId());
        cv.put(DBOpenHelper.MEME_TITLE , memeModel.getTitle());
        cv.put(DBOpenHelper.MEME_AUTHORID , memeModel.getAuthorId());
        cv.put(DBOpenHelper.MEME_DOWNLOAD_URL , memeModel.getImageUrl());


        writableMemeDatas.update(DBOpenHelper.MEME_TABLE , cv , DBOpenHelper.MEME_ID+"="+memeModel.getMemeId() , null);
        return true;
    }

    /**
     * Meme Synchronizical function to sync datas in repo and database
     * @param toBeSinced
     * @return
     */
    public ArrayList<MemeModel> syncMeme(ArrayList<MemeModel> toBeSinced) {
        if (toBeSinced == null || readableMemeDatas == null) return getAllLocalMemes();
        for (int i = 0 ; i < toBeSinced.size() ; i++) {
            addMeme(toBeSinced.get(i));
        }
        return getAllLocalMemes();
    }


}