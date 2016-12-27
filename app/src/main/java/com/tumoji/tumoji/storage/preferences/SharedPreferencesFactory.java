package com.tumoji.tumoji.storage.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Author: perqin
 * Date  : 12/28/16
 */

public final class SharedPreferencesFactory {
    public static final String PK_ACCOUNT_TOKEN = "ACCOUNT_TOKEN";

    private SharedPreferencesFactory() {
        // Prevent construction
    }

    public static SharedPreferences getAppDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }
}
