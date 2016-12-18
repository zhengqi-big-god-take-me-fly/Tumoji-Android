package com.tumoji.tumoji.common;

/**
 * Author: perqin
 * Date  : 12/18/16
 */

public interface OnGetResultListener<T> {
    /**
     * Successfully get things
     * @param result The things to get
     */
    void onSuccess(T result);

    /**
     * Fail to get things
     * @param error Error code
     * @param msg Error message
     */
    void onFailure(int error, String msg);
}
