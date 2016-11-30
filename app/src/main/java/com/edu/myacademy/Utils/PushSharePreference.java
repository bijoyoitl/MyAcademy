package com.edu.myacademy.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ripon on 11/10/2016.
 */

public class PushSharePreference {
    private SharedPreferences sharedPreferences;
    public Context context;

    public PushSharePreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("pushShare", Context.MODE_PRIVATE);
    }

    public void savePushStatusId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("p_status", id);
        editor.apply();
        editor.commit();
    }

    public String getPushStatusId() {
        return sharedPreferences.getString("p_status", "");
    }
}
