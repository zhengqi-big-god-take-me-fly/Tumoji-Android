package com.tumoji.tumoji.data.settings.repository;

/**
 * Author: perqin
 * Date  : 1/7/17
 */

public interface ISettingsRepository {
    String PK_SETTINGS_MEME_DIRECTORY = "SETTINGS_MEME_DIRECTORY";

    String getSettingString(String key);

    void putSetting(String key, String value);
}
