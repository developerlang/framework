package com.framework.db;

import java.util.List;

import android.content.Context;

import com.framework.dao.DaoMaster;
import com.framework.dao.DaoMaster.OpenHelper;
import com.framework.dao.DaoSession;
import com.framework.dao.Weather;
import com.framework.dao.WeatherDao;

public class DBHelper {
    private static final String TAG = "DBHelper";
    /**
     * 数据库版本号。修改数据库表结构要增加版本号。
     */
    public static final int DATABASE_VERSION = 14;
    /**
     * 数据库名称
     */
    public static final String DATABASE_NAME = "db";

    private static DBHelper sInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private WeatherDao mWeatherDao;

    private DBHelper() {
    }

    public static DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper();
            sInstance.init(context);
        }
        return sInstance;
    }

    public void init(Context context) {
        OpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
        mDaoMaster = new DaoMaster(helper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        mWeatherDao = mDaoSession.getWeatherDao();
    }

    /** 添加数据 */
    public void addWeather(Weather item) {
        if (mWeatherDao == null || item == null) {
            return;
        }
        mWeatherDao.insert(item);
    }

    /** 查询 */
    public List<Weather> getWeather() {
        if (mWeatherDao == null) {
            return null;
        }
        return mWeatherDao.loadAll();
    }
}