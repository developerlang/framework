package com.framework;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.util.Log;

public class BaseUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private static final String TAG = "BaseUncaughtExceptionHandler";
    protected Context mContext;

    public BaseUncaughtExceptionHandler(Context context) {
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String stackTrace = getStackTrace(ex);
        
        //TODO: save into file
        Log.d(TAG, stackTrace);

        System.exit(2);
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}