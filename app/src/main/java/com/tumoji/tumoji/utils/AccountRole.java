package com.tumoji.tumoji.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by souler on 16-12-17.
 */
public class AccountRole implements Serializable {
    @SerializedName("roles")
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
