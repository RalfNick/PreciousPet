package com.jess.arms.utils.file_util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UriUtils
 * @email wanglixin@icourt.cc
 * @date 2018/06/02 下午7:23
 **/
public final class UriUtils {

    private UriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 将String路径转换为Uri路径
     *
     * @param path 文件路径
     * @return
     */
    public static Uri getPathUri(Context context, String path) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider",
                    new File(path));
        } else {
            uri = Uri.fromFile(new File(path));
        }
        return uri;
    }

    /**
     * Return a content URI for a given file.
     *
     * @param file The file.
     * @return a content URI for a given file
     */
    public static Uri getUriForFile(Context context, final File file) {
        if (file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = context.getPackageName() + ".utilcode.provider";
            return FileProvider.getUriForFile(context, authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}
                    , null, null, null);

            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
