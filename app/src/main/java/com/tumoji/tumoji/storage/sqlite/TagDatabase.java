package com.tumoji.tumoji.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.provider.Telephony;

import com.tumoji.tumoji.TumojiApp;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by souler on 16-12-14.
 */
public class TagDatabase {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase readableTagDatas;
    private SQLiteDatabase writableTagDatas;
    public static TagDatabase tagDatabase;

    public static TagDatabase getInstance() {
        if (tagDatabase == null) tagDatabase = new TagDatabase(new DBOpenHelper(TumojiApp.myContext));
        return tagDatabase;
    }

    private TagDatabase(DBOpenHelper openHelper) {
        super();
        dbOpenHelper = openHelper;
        writableTagDatas = openHelper.getWritableDatabase();
        readableTagDatas = openHelper.getReadableDatabase();
    }

    /**
     * Get all local Tags
     * @return a list of Tagmodel
     */
    public ArrayList<TagModel> getAllLocalTags() {
        ArrayList<TagModel> ls = new ArrayList<>();
        if (readableTagDatas == null) return null;

        /**
         * Perform query
         */
        Cursor tagsCursor = readableTagDatas.rawQuery("select * from " + DBOpenHelper.TAG_TABLE , null);
        while (tagsCursor.moveToNext()) {
            String tagName = tagsCursor.getString(tagsCursor.getColumnIndex(DBOpenHelper.TAG_NAME));
            String tagDescription = tagsCursor.getString(tagsCursor.getColumnIndex(DBOpenHelper.TAG_DESCRIPTION));
            TagModel tempModel = new TagModel(tagName,tagDescription);
            ls.add(tempModel);
        }
        tagsCursor.close();
        return ls;

    }

    /**
     * Get tag by name
     * @param name
     * @return tagmodel tgm
     */
    public TagModel getTagByName(String name) {
        if (readableTagDatas == null) return null;
        TagModel tempModel = null;

        /**
         * Perform query
         */
        Cursor cursor = readableTagDatas.rawQuery("select * from " + DBOpenHelper.TAG_TABLE + " where " + DBOpenHelper.TAG_NAME + "=" + name , null);
        if (cursor.moveToNext()) {
            String tagName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.TAG_NAME));
            String tagDescription = cursor.getString(cursor.getColumnIndex(DBOpenHelper.TAG_DESCRIPTION));
            tempModel = new TagModel(tagName,tagDescription);
            cursor.close();
        }
        return tempModel;
    }


    /**
     * Tag Existence Checking function
     * @param name
     * @return boolean value of existency in name in database
     */
    private boolean checkTagExistence(String name) {
        if (writableTagDatas == null) return false;
        String queryStatement = "name=";
        Cursor cursor = writableTagDatas.query(DBOpenHelper.TAG_TABLE , new String[] {
                DBOpenHelper.TAG_NAME , DBOpenHelper.TAG_DESCRIPTION} , queryStatement , new String[] {
                name} , null , null , null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

    /**
     * Tag adding function
     * @param tag
     * @return success or not
     */
    public boolean addTag(TagModel tag) {
        if (writableTagDatas == null || tag == null || checkTagExistence(tag.getTagName())) return false;

        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.TAG_NAME , tag.getTagName());
        cv.put(DBOpenHelper.TAG_DESCRIPTION , tag.getDescription());

        writableTagDatas.insert(DBOpenHelper.TAG_TABLE , null , cv);
        return true;
    }


    /**
     * Tag removal function
     * @param tag
     * @return success or not
     */
    public boolean removeTag(TagModel tag) {
        if (writableTagDatas == null || tag == null || !checkTagExistence(tag.getTagName())) return false;

        writableTagDatas.delete(DBOpenHelper.TAG_TABLE , DBOpenHelper.TAG_NAME + "=?" , new String []{tag.getTagName()});

        return true;

    }


    /**
     * Tag updation Function
     * @param updateTag
     * @return success or not
     */
    public boolean updateTag(TagModel updateTag) {
        if (writableTagDatas == null || updateTag == null || !checkTagExistence(updateTag.getTagName())) return false;

        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.TAG_NAME , updateTag.getTagName());
        cv.put(DBOpenHelper.TAG_DESCRIPTION , updateTag.getDescription());

        writableTagDatas.update(DBOpenHelper.TAG_TABLE , cv , DBOpenHelper.TAG_NAME+"="+updateTag.getTagName() , null);
        return true;
    }

    /**
     * Tag Synchronizical function to sync datas in repo and database
     * @param toBeSinced
     * @return
     */
    public ArrayList<TagModel> syncTag(ArrayList<TagModel> toBeSinced) {
        if (toBeSinced == null || readableTagDatas == null) return getAllLocalTags();
        for (int i = 0 ; i < toBeSinced.size() ; i++) {
            addTag(toBeSinced.get(i));
        }
        return getAllLocalTags();
    }


}