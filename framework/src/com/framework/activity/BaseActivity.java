
package com.framework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.framework.log.MyLogger;
import com.framework.network.RequestManager;
import com.framework.network.VolleyErrorHelper;

/**
 * 基类
 * 
 * @author heiyl
 */
public class BaseActivity extends FragmentActivity {
    protected Activity activity;
    protected MyLogger hlog = MyLogger.hLog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    @Override
    public void onStop() {
        super.onStop();
        // 当当前Activity关闭时取消所有在当前Activity的网络请求
        RequestManager.cancelAll(this);
    }

    /**
     * 添加网络请求到消息队列
     * 
     * @param request
     */
    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

    /**
     * 网络请求失败的监听
     * 
     * @return
     */
    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, VolleyErrorHelper.getMessage(error, activity), Toast.LENGTH_LONG).show();
            }
        };
    }
}
