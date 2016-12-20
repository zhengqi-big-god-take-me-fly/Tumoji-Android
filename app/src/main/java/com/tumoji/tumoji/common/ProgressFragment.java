package com.tumoji.tumoji.common;

/**
 * Author: perqin
 * Date  : 12/21/16
 */

public interface ProgressFragment {
    void showError(int res);

    void startLoading();

    void stopLoading();
}
