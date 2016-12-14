package com.tumoji.tumoji.storage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by souler on 16-12-14.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tumoji.db";

    /**\
     * Constant for Meme tables
     */
    public static final String MEME_TABLE = "memes";
    public static final String MEME_ID = "id";
    public static final String MEME_TITLE = "title";
    public static final String MEME_AUTHORID = "authorId";
    public static final String MEME_DOWNLOAD_URL = "imageUrl";


    /**
     * Constant for Tags
     */
    public static final String TAG_TABLE = "tags";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESCRIPTION = "desc";


    /**
     * Constant for DATABASE OPERATIONS
     */
    public static final String IPAI = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String TNN = "TEXT NOT NULL";

    /**
     *
     * @param context
     */
    public DBOpenHelper(Context context) {
        super(context , DATABASE_NAME , null ,1);
    }


    /**
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        /**
         * Create MEME table
         */
        db.execSQL("CREATE TABLE if not exists " + MEME_TABLE + "("
                + MEME_ID + " " + IPAI + ","
                + MEME_TITLE + " " + TNN + ","
                + MEME_AUTHORID + " " + TNN + ","
                + MEME_DOWNLOAD_URL + " " + TNN + ")");

        /**
         * Create Tag table
         */
        db.execSQL("CREATE TABLE if not exists " + TAG_TABLE + "("
                + TAG_ID + " " + IPAI + ","
                + TAG_NAME + " " + TNN + ","
                + TAG_DESCRIPTION + " " + TNN + ")");
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
