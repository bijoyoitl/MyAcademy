package com.edu.myacademy.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.edu.myacademy.HomeActivity;
import com.edu.myacademy.Model.RegisterInfo;
import com.edu.myacademy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import dmax.dialog.SpotsDialog;

/**
 * Created by Bijoy on 10/19/2016.
 */

public class RegistrationAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    private RegisterInfo registerInfo;
    private AlertDialog progressDialog;

    public RegistrationAsyncTask(Context context) {
        this.context = context;
        progressDialog = new SpotsDialog(context, R.style.Custom);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }
    @Override
    protected String doInBackground(String... params) {
//        String response = new HTTPPostHelper().sendPostRequest(BaseUrl.url, registerInfo);
//        return response;
        HashMap<String, String> paramsHashMap =new HashMap<>();
        paramsHashMap.put("user_name",params[0]);
        paramsHashMap.put("user_id",params[1]);
        paramsHashMap.put("user_type",params[2]);
        paramsHashMap.put("user_class",params[3]);
        paramsHashMap.put("password",params[4]);
        paramsHashMap.put("confirm_pass",params[5]);
        paramsHashMap.put("request_for",params[6]);
        paramsHashMap.put("devicetype",params[7]);
        paramsHashMap.put("deviceid",params[8]);

        String response = new HTTPPostHelper().sendPostRequest(BaseUrl.url,paramsHashMap);
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e(" Return ", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String status = jsonObject.getString("status");

            if (status.equals("0")){
                String error = jsonObject.getString("err");
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }else {

                String user_id = jsonObject.getString("user_id");
                String user_name = jsonObject.getString("user_name");

                new MyAcademySharePreference(context).saveUserInfo(false,user_id,user_name,"");
                Intent intent = new Intent(context,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                ((Activity)context).finish();
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
