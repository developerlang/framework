
package com.framework;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

public class BaseApplication extends Application {

    public static final String TAG = "Volley";

    private RequestQueue mRequestQueue;

    /**
     * A singleton instance of the application class for easy access in other
     * places
     */
    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static synchronized BaseApplication getInstance() {
        return sInstance;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be created
        // when it is accessed for the first time
        if (mRequestQueue == null) {
            synchronized (BaseApplication.class) {
                if (mRequestQueue == null) {
                    mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                }
            }
        }
        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified then
     * it is used else Default TAG is used.
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue: %s", request.getUrl());
        getRequestQueue().add(request);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     */
    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important to
     * specify a TAG so that the pending/ongoing requests can be cancelled.
     * 
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
