package com.framework.util;

import java.io.File;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;

public class AppUtils {
    /**
     * 判断当前网络是否是3G/4G/WIFI快速网络
     */
    public static boolean isFastNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isConnected()) {
            return false;
        }
        if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }

        if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            switch (activeNetInfo.getSubtype()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
                /*
                 * Above API level 7, make sure to set android:targetSdkVersion
                 * to appropriate level to use these
                 */
            case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return false;
            }
        }
        return false;
    }

    public static String getCacheAbsolutePath(Context context) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir
        // otherwise use internal cache dir
        boolean shouldUseExternalCache = Environment.MEDIA_MOUNTED
                .equals(Environment.getExternalStorageState())
                || !isExternalStorageRemovable();

        File fileCacheDir = shouldUseExternalCache ? getExternalCacheDir(context)
                : context.getCacheDir();

        if (fileCacheDir == null) {
            fileCacheDir = context.getCacheDir();
        }

        final String cachePath = fileCacheDir.getPath();

        return cachePath + File.separator;
    }

    /**
     * Check if external storage is built-in or removable.
     * 
     * @return True if external storage is removable (like an SD card), false
     *         otherwise.
     */
    @TargetApi(9)
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    /**
     * Get the external app cache directory.
     * 
     * @param context
     *            The context to use
     * @return The external cache dir
     */
    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            if (Environment.MEDIA_MOUNTED.equals(Environment
                    .getExternalStorageState())) {
                return context.getExternalCacheDir();
            }
        }

        return null;
    }

    /**
     * 获取应用的versionName。
     * 
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * 获取应用的包名
     * 
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
