
package com.framework;

import android.app.Application;

import com.framework.network.RequestManager;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        RequestManager.init(this);
    }
}
