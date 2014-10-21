package com.framework.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.framework.R;
import com.framework.bean.WeatherInfo;
import com.framework.common.Config;
import com.framework.dao.Weather;
import com.framework.db.DBHelper;
import com.framework.model.WeatherData;
import com.framework.network.GsonRequest;

import java.util.List;

public class MainActivity extends BaseActivity {
    
    private static final String TAG = "MainActivity";
    private static final boolean TEST_DB = false;

    private Button mGetWeatherSKBtn;
    private TextView mWeatherSKInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                if (TEST_DB) {
                    List<Weather> weathers = getWeatherFromDB();
                    if (weathers != null && weathers.size() > 0) {
                        hlog.d("weather on db size: " + weathers.size() + " city:" + weathers.get(0).getCity());
//                        Log.d(TAG, "weather on db size: " + weathers.size() + " city:" + weathers.get(0).getCity()); 
                    } else {
                        hlog.d("weather on db is empty");
//                        Log.d(TAG, "weather on db is empty"); 
                    }
                }
            }
        });
    }

    private void getWeatherInfo() {
        GsonRequest<WeatherData> getWeatherInfoRequest = new GsonRequest<WeatherData>(Method.GET, Config.WEATHER_SK_API,
                WeatherData.class, null, new Listener<WeatherData>() {
                    @Override
                    public void onResponse(WeatherData weatherData) {
                        hlog.d("response : " + weatherData == null ? "empty" : weatherData.toString());
//                        Log.d(TAG, "response : " + weatherData == null ? "empty" : weatherData.toString());
                        onHandlResponse(weatherData);
                    }
                }, errorListener());
        executeRequest(getWeatherInfoRequest);
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
                if (TEST_DB) {
                    saveWeatherInfo(weatherData.getWeatherinfo());
                }
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

    private void saveWeatherInfo(WeatherInfo weatherInfo) {
        Weather weather = new Weather(System.currentTimeMillis(), weatherInfo.getCityid(), weatherInfo.getCity(),
                weatherInfo.getTemp());
        DBHelper.getInstance(getApplicationContext()).addWeather(weather);
    }
    
    private List<Weather> getWeatherFromDB() {
        return DBHelper.getInstance(getApplicationContext()).getWeather();
    }
}
