package com.framework.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class DeviceUtils {
    /**
     * <p>
     * <b>IMEI.</b>
     * </p>
     * Returns the unique device ID, for example, the IMEI for GSM and the MEID
     * or ESN for CDMA phones. Return null if device ID is not available.
     * 
     * Requires Permission: READ_PHONE_STATE
     * 
     * @param context
     * @return
     */
    public synchronized static String getDeviceId(Context context) {
        if (context == null) {
            return "";
        }

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null || TextUtils.isEmpty(tm.getDeviceId())) {
            // 双卡双待需要通过phone1和phone2获取imei，默认取phone1的imei。
            tm = (TelephonyManager) context.getSystemService("phone1");
        }

        String imei = null;
        if (tm != null) {
            imei = tm.getDeviceId();
        }

        return imei;
    }

    /**
     * Returns the serial number of the SIM, if applicable. Return null if it is
     * unavailable.
     * 
     * Requires Permission: READ_PHONE_STATE
     * 
     * @param context
     * @return
     */
    public synchronized static String getSimSerialNumber(Context context) {
        if (context == null) {
            return "";
        }
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    /**
     * A 64-bit number (as a hex string) that is randomly generated on the
     * device's first boot and should remain constant for the lifetime of the
     * device. (The value may change if a factory reset is performed on the
     * device.)
     * 
     * @param context
     * @return
     */
    public synchronized static String getAndroidID(Context context) {
        return android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
    }
}
