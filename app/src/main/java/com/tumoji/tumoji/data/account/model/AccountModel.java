package com.tumoji.tumoji.data.account.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: perqin
 * Date  : 12/14/16
 *
 * This model stands for the local user account
 */

public class AccountModel implements Serializable {
    @SerializedName("id")
    private String userId;
    @SerializedName("avatar")
    private String avatarUrl;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;

    public AccountModel() {
        this("", "", "", "");
    }

    public AccountModel(String userId, String avatarUrl, String username, String email) {
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AccountModel withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public AccountModel withAvatarUrl(String avatarUrl) {
        setAvatarUrl(avatarUrl);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountModel withUsername(String username) {
        setUsername(username);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountModel withEmail(String email) {
        setEmail(email);
        return this;
    }
}
