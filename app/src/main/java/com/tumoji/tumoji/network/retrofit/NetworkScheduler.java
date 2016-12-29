package com.tumoji.tumoji.network.retrofit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: perqin
 * Date  : 12/28/16
 *
 * A transformer for Retrofit rx.
 * @see <a href="http://blog.danlew.net/2015/03/02/dont-break-the-chain/">Don't break the chain: use RxJava's compose() operator</a>
 */

public class NetworkScheduler {
    public static <T>Observable.Transformer<T, T> applySchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
