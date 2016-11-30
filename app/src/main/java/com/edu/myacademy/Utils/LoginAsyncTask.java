package com.edu.myacademy.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.edu.myacademy.HomeActivity;
import com.edu.myacademy.LoginActivity;
import com.edu.myacademy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import dmax.dialog.SpotsDialog;

/**
 * Created by Bijoy on 10/20/2016.
 */

public class LoginAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    private AlertDialog progressDialog;
    boolean loginStatus;

    public LoginAsyncTask(Context context, boolean loginStatus) {
        this.context = context;
        this.loginStatus = loginStatus;
        progressDialog = new SpotsDialog(context, R.style.Custom);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        HashMap<String, String> paramsHashMap = new HashMap<>();
        paramsHashMap.put("user_id", params[0]);
        paramsHashMap.put("password", params[1]);
        paramsHashMap.put("request_for", params[2]);
        paramsHashMap.put("devicetype", params[3]);
        paramsHashMap.put("deviceid", params[4]);

        String response = new HTTPPostHelper().sendPostRequest(BaseUrl.url, paramsHashMap);
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String status = jsonObject.getString("status");
            if (status.equals("1")) {
                String user_id = jsonObject.getString("user_id");
                String user_name = jsonObject.getString("user_name");
                String user_image = jsonObject.getString("user_img");

                new MyAcademySharePreference(context).saveUserInfo(loginStatus, user_id, user_name, user_image);
                Log.e("LAT", " img : " + user_image);
                Intent intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                ((LoginActivity) context).finish();
            } else {
                String error = jsonObject.getString("err");
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Toast.makeText(context, "VALUE", Toast.LENGTH_SHORT).show();
    }
}
