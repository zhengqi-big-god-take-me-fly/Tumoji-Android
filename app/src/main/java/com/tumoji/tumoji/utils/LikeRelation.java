package com.tumoji.tumoji.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by souler on 16-12-16.
 */
public class LikeRelation implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("userId")
    private String userId;
    @SerializedName("expressionId")
    private String expressionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }
}
