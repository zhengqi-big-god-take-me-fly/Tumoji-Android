package com.tumoji.tumoji.common;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showUnexpectedError(String message);
}
