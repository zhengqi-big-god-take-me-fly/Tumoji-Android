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
     * Constant for Accounts
     */
    public static final String ACCOUNT_TABLE = "accounts";
    public static final String ACCOUNT_ID = "id";
    public static final String ACCOUNT_NAME = "username";
    public static final String ACCOUNT_AVATAR_URL = "avatar";
    public static final String ACCOUNT_EMAIL = "email";

    /**
     * Constant for DATABASE OPERATIONS
     */
    public static final String IPAI = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String TNN = "TEXT NOT NULL";
    // FIXME: Reset to 1 after first release
    private static final int DB_VERSION = 3;

    private static DBOpenHelper sInstance;

    public static DBOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBOpenHelper(context);
        }
        return sInstance;
    }

    /**
     *
     * @param context
     */
    public DBOpenHelper(Context context) {
        super(context , DATABASE_NAME , null , DB_VERSION);
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
                + MEME_ID + " " + "TEXT PRIMARY KEY" + ","
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


        /**
         * Create User table
         * NOTE: We force cached user to have a user ID, so AUTOINCREMENT is not needed
         */
        db.execSQL("CREATE TABLE if not exists " + ACCOUNT_TABLE + "("
                + ACCOUNT_ID + " " + "TEXT PRIMARY KEY" + ","
                + ACCOUNT_NAME + " " + TNN + ","
                + ACCOUNT_EMAIL + " " + TNN + ","
                + ACCOUNT_AVATAR_URL + " " + TNN + ")");
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Simply drop old tables
        // FIXME ON RELEASE: Don't forget to migrate old data to new database
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", ACCOUNT_TABLE));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", MEME_TABLE));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TAG_TABLE));
        onCreate(db);
    }
}
