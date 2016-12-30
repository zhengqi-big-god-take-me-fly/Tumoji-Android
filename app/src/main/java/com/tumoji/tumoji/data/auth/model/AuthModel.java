package com.tumoji.tumoji.data.auth.model;

import java.io.Serializable;

/**
 * Author: perqin
 * Date  : 12/14/16
 *
 * This model stands for the user auth data
 */

public class AuthModel implements Serializable {
    private String userId;
    private String accessToken;

    public AuthModel() {
        this("", "");
    }

    public AuthModel(String userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AuthModel withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthModel withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }
}
