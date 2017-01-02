package com.tumoji.tumoji.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Author: perqin
 * Date  : 1/2/17
 */

public final class FileUtils {
    private FileUtils() {
        // Prevent construction
    }

    public static File fromUri(Context context, Uri uri) {
        File file = null;
        Cursor cursor = context.getContentResolver().query(uri, new String[]{ MediaStore.Images.Media.DATA }, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                file = new File(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
            }
            cursor.close();
        }
        return file;
    }
}
