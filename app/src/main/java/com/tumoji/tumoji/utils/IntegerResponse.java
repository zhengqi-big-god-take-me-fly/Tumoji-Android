package com.tumoji.tumoji.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by souler on 16-12-15.
 */
public class IntegerResponse implements Serializable{
    @SerializedName("count")
    private int integerResponse;

    public int getIntegerResponse() {
        return integerResponse;
    }

    public void setIntegerResponse(int integerResponse) {
        this.integerResponse = integerResponse;
    }
}
