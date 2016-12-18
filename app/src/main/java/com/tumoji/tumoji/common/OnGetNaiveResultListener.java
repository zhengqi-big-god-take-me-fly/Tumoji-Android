package com.tumoji.tumoji.common;

/**
 * Author: perqin
 * Date  : 12/18/16
 */
public interface OnGetNaiveResultListener {
    /**
     * Successful
     */
    void onSuccess();

    /**
     * Fail
     * @param error Error code
     * @param msg Error message
     */
    void onFailure(int error, String msg);
}
