package com.tumoji.tumoji.data.user.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: perqin
 * Date  : 12/30/16
 */

public class UserModel implements Serializable {
    @SerializedName("id")
    private String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("avatar")
    private String avatarUrl;

    public UserModel() {
        this("", "", "", "");
    }

    public UserModel(String userId, String username, String email, String avatarUrl) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserModel withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserModel withUsername(String username) {
        setUsername(username);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel withEmail(String email) {
        setEmail(email);
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UserModel withAvatarUrl(String avatarUrl) {
        setAvatarUrl(avatarUrl);
        return this;
    }
}
