package com.irobotix.architecture.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * 常用工具
 */
public final class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    public static Context getContext() {
        if (null != context) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }
}
