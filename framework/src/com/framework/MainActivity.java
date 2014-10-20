package com.framework;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.framework.common.Config;
import com.framework.model.WeatherData;
import com.framework.network.GsonRequest;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private RequestQueue mRequestQueue;
    private Button mGetWeatherSKBtn;
    private TextView mWeatherSKInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mRequestQueue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGetWeatherSKBtn = (Button) this.findViewById(R.id.weather_sk_get_btn);
        mWeatherSKInfo = (TextView) this.findViewById(R.id.weather_sk_info);

        mGetWeatherSKBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mGetWeatherSKBtn.setEnabled(false);
                mGetWeatherSKBtn.setText(R.string.weather_sk_getting_tip);
                getWeatherInfo();

            }
        });
    }

    private void getWeatherInfo() {
        GsonRequest<WeatherData> getWeatherInfoRequest = new GsonRequest<WeatherData>(Config.WEATHER_SK_API,
                WeatherData.class, null, new Listener<WeatherData>() {
                    @Override
                    public void onResponse(WeatherData weatherData) {
                        Log.e(TAG, "response : " + weatherData == null ? "empty" : weatherData.toString());
                        onHandlResponse(weatherData);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = error.getMessage();
                        Log.e(TAG, "err : " + err);
                        onHandleResponseError(err);
                    }
                });
        mRequestQueue.add(getWeatherInfoRequest);
    }

    private void onHandlResponse(WeatherData weatherData) {
        if (this != null && mGetWeatherSKBtn != null) {
            mGetWeatherSKBtn.setEnabled(true);
            mGetWeatherSKBtn.setText(R.string.weather_sk_tip);
            if (weatherData == null || weatherData.getWeatherinfo() == null) {
                mWeatherSKInfo.setText(R.string.weather_sk_getting_empty_tip);
            } else {
                mWeatherSKInfo.setText(String.format(this.getString(R.string.weather_sk_info_format), weatherData
                        .getWeatherinfo().getTemp(), weatherData.getWeatherinfo().getTime()));
            }
        }
    }

    private void onHandleResponseError(String message) {
        if (this != null && mGetWeatherSKBtn != null) {
            mGetWeatherSKBtn.setEnabled(true);
            mGetWeatherSKBtn.setText(R.string.weather_sk_tip);
            mWeatherSKInfo.setText(String.format(this.getString(R.string.weather_sk_getting_error_tip), message));
        }
    }
}
