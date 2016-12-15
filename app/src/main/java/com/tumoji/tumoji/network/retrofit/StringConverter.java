package com.tumoji.tumoji.network.retrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by souler on 16-12-15.
 */
public class StringConverter implements Converter<ResponseBody , String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
