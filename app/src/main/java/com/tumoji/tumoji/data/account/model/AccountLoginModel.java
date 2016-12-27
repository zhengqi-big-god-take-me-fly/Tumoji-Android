package com.tumoji.tumoji.data.account.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: souler
 * Date  : 16-12-17
 */
public class AccountLoginModel implements Serializable {

    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
