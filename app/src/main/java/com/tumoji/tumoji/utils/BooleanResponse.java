package com.tumoji.tumoji.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by souler on 16-12-15.
 */
public class BooleanResponse implements Serializable {
    @SerializedName("exists")
    private boolean booleanValue;

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}
