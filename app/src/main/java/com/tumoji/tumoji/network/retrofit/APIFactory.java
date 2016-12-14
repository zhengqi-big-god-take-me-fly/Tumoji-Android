package com.tumoji.tumoji.network.retrofit;

import java.util.Objects;

/**
 * Created by souler on 16-12-14.
 */
public class APIFactory {
    protected static final Object monitor = new Object();
    private static MemeAPI memeAPISingleton = null;
    private static TagAPI tagAPISingleton = null;

    /**
     * Singleton of memeAPI
     */
    public static MemeAPI getMemeAPIInstance() {
        synchronized (monitor) {
            if (memeAPISingleton == null) {
                memeAPISingleton = new RetrofitAPI().getMemeService();
            }
            return memeAPISingleton;
        }
    }

    /**
     * Singleton of tagAPI
     */
    public static TagAPI getTagAPIInstance() {
        synchronized (monitor) {
            if (tagAPISingleton == null) {
                tagAPISingleton = new RetrofitAPI().getTagService();
            }
            return tagAPISingleton;
        }
    }
}
