
package com.framework;

import android.app.Application;
import android.content.Context;

import com.framework.network.RequestManager;

public class BaseApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init();
    }

    private void init() {
        RequestManager.init(this);
    }
    
    public static Context getContext() {
        return mContext;
    }
}
