package com.tumoji.tumoji.data.account.model;

/**
 * Author: perqin
 * Date  : 12/28/16
 *
 * Model used to represent the result of finding a user by email or username.
 */

public class FindAccountResultModel {
    public static final int RESULT_NOT_FOUND = -1;
    public static final int RESULT_USERNAME_FOUND = 0;
    public static final int RESULT_EMAIL_FOUND = 1;
    private int result;

    public FindAccountResultModel() {
        this(RESULT_NOT_FOUND);
    }

    public FindAccountResultModel(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
