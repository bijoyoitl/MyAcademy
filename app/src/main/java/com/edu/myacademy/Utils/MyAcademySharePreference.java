package com.edu.myacademy.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ripon on 10/25/2016.
 */

public class MyAcademySharePreference {

    private SharedPreferences sharedPreferences;
    public Context context;

    public MyAcademySharePreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("myAcademyShare", Context.MODE_PRIVATE);
    }

    public void saveUserInfo(boolean status, String user_id, String user_name, String ima_url) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("status", status);
        editor.putString("user_id", user_id);
        editor.putString("user_name", user_name);
        editor.putString("image_url", ima_url);
        editor.apply();
        editor.commit();
    }

    public void saveDeviceId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("deviceId", id);
        editor.apply();
        editor.commit();
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
        editor.commit();
    }

    public String getDeviceId() {
        return sharedPreferences.getString("deviceId", "");
    }

    public String getUserId() {
        return sharedPreferences.getString("user_id", "");
    }

    public String getUserName() {
        return sharedPreferences.getString("user_name", "");
    }

    public String getUserImageUrl() {
        return sharedPreferences.getString("image_url", "");
    }

    public boolean getStatus() {
        return sharedPreferences.getBoolean("status", false);
    }

    public String getToken() {
        return sharedPreferences.getString("token", "");
    }
}
