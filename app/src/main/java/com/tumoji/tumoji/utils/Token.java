package com.tumoji.tumoji.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by souler on 16-12-16.
 */

public class Token implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("ttl")
    private int ttl;
    @SerializedName("created")
    private String created;
    @SerializedName("userId")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
