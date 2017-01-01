package com.tumoji.tumoji.data.user.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: perqin
 * Date  : 1/1/17
 */

public class SignUpUserModel extends UserModel implements Serializable {
    @SerializedName("password")
    private String password;

    public SignUpUserModel() {
        this("", "", "", "", "");
    }

    public SignUpUserModel(String userId, String username, String email, String avatarUrl, String password) {
        super(userId, username, email, avatarUrl);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUpUserModel withPassword(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public SignUpUserModel withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    @Override
    public SignUpUserModel withUsername(String username) {
        setUsername(username);
        return this;
    }

    @Override
    public SignUpUserModel withEmail(String email) {
        setEmail(email);
        return this;
    }

    @Override
    public SignUpUserModel withAvatarUrl(String avatarUrl) {
        setAvatarUrl(avatarUrl);
        return this;
    }
}
