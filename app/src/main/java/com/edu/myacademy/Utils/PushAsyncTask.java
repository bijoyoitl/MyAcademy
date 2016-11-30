package com.edu.myacademy.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ripon on 11/9/2016.
 */

public class PushAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;

    public PushAsyncTask(Context context) {
        this.context = context;
        Log.e("PA", "doInBackground con");
    }

    @Override
    protected String doInBackground(String... params) {
        Log.e("PA", "doInBackground");
        HashMap<String, String> paramsHashMap = new HashMap<>();
        paramsHashMap.put("devicetype", params[0]);
        paramsHashMap.put("deviceid", params[1]);
        paramsHashMap.put("pushId", params[2]);
        paramsHashMap.put("request_for", params[3]);
        Log.e("PAT", "param : " + params[0] + params[1] + params[2] + params[3]);
        String response = new HTTPPostHelper().sendPostRequest(BaseUrl.url, paramsHashMap);
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("PAT","status : "+s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String push_status = jsonObject.getString("status");
            PushSharePreference pushSharePreference = new PushSharePreference(context);
            pushSharePreference.savePushStatusId(push_status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
