package com.tumoji.tumoji.data.user.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: perqin
 * Date  : 1/1/17
 */

public class SignUpUserModel implements Serializable {
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public SignUpUserModel() {
        this("", "", "");
    }

    public SignUpUserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SignUpUserModel withUsername(String username) {
        setUsername(username);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SignUpUserModel withEmail(String email) {
        setEmail(email);
        return this;
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
}
