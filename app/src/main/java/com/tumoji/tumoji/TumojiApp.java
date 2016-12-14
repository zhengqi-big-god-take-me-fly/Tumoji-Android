package com.tumoji.tumoji;

import android.app.Application;
import android.content.Context;

/**
 * Created by souler on 16-12-14.
 */
public class TumojiApp extends Application {
    private static final String DB_NAME = "tumoji.db";
    public static Context myContext;
    @Override
    public void onCreate() {
        super.onCreate();
        myContext = getApplicationContext();
    }
}
