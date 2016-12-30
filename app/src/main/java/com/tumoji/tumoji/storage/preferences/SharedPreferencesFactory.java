package com.tumoji.tumoji.storage.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Author: perqin
 * Date  : 12/28/16
 */

public final class SharedPreferencesFactory {
    public static final String PK_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String PK_SIGNED_IN_USER_ID = "SIGNED_IN_USER_ID";

    private SharedPreferencesFactory() {
        // Prevent construction
    }

    public static SharedPreferences getAppDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }
}
