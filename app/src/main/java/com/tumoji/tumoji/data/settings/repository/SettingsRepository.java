package com.tumoji.tumoji.data.settings.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.tumoji.tumoji.storage.preferences.SharedPreferencesFactory;

import java.io.File;

/**
 * Author: perqin
 * Date  : 1/7/17
 */

public class SettingsRepository implements ISettingsRepository {
    private static SettingsRepository sInstance;

    private SharedPreferences mSharedPreferences;

    public static SettingsRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SettingsRepository(context);
        }
        return sInstance;
    }

    private SettingsRepository(Context context) {
        mSharedPreferences = SharedPreferencesFactory.getAppDefaultSharedPreferences(context);
        // Prepare default settings
        if (getSettingString(PK_SETTINGS_MEME_DIRECTORY) == null) {
            putSetting(PK_SETTINGS_MEME_DIRECTORY, new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Tumoji").getAbsolutePath());
        }
    }

    @Override
    public String getSettingString(String key) {
        return mSharedPreferences.getString(key, null);
    }

    @Override
    public void putSetting(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }
}
