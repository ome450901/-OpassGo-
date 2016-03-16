package com.willy.smartcityhackathon;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.willy.smartcityhackathon.object.api.FoodCondition;

/**
 * Created by Willy on 2015/11/12.
 */

public class App extends Application {

    private static App mInstance;
    private RequestQueue mVolleyRequestQueue = null;

    private SharedPreferences mSharedPreferences;

    public static FoodCondition sFoodCondition = new FoodCondition();
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public RequestQueue getVolleyRequestQueue() {

        if (mVolleyRequestQueue == null) {
            mVolleyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mVolleyRequestQueue;
    }

    public Display getDisplay() {
        return ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }

    public boolean getPreferenceBooleanValue(String preferenceName, boolean defaultValue) {
        return getSharedPreferences().getBoolean(preferenceName, defaultValue);
    }

    public SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        return mSharedPreferences;
    }
}
