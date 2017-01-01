package com.tumoji.tumoji.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: perqin
 * Date  : 12/28/16
 *
 * A transformer for Retrofit rx and Database access.
 * @see <a href="http://blog.danlew.net/2015/03/02/dont-break-the-chain/">Don't break the chain: use RxJava's compose() operator</a>
 */
public final class ApplySchedulers {
    private ApplySchedulers() {
        // Prevent construction
    }

    public static <T>Observable.Transformer<T, T> network() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T>Observable.Transformer<T, T> storage() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
