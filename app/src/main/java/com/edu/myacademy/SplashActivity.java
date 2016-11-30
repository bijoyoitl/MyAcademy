package com.edu.myacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.edu.myacademy.DialogFragments.ConnectionAlertDialog;
import com.edu.myacademy.Utils.ConnectionDetector;
import com.edu.myacademy.Utils.MyAcademySharePreference;
import com.edu.myacademy.Utils.PushAsyncTask;
import com.edu.myacademy.Utils.PushSharePreference;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private MyAcademySharePreference sharePreference;
    private PushSharePreference pushSharePreference;
    ConnectionDetector detector;
    Boolean isInternet = false;
    String token = "";
    public String device_id = "";
    String push_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        detector = new ConnectionDetector(this);
        sharePreference = new MyAcademySharePreference(this);
        pushSharePreference = new PushSharePreference(this);
        isInternet = detector.isConnectingToInternet();
        token = sharePreference.getToken();
        push_status = pushSharePreference.getPushStatusId();
        device_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        sharePreference.saveDeviceId(device_id);

        if (isInternet) {
            if (sharePreference.getStatus()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (push_status.equals("0") || push_status.equals("")) {
                            new PushAsyncTask(SplashActivity.this).execute("android", sharePreference.getDeviceId(), token, "push");
                        }

                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        SplashActivity.this.finish();
                    }
                }, SPLASH_TIME_OUT);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (push_status.equals("0") || push_status.equals("")) {
                            new PushAsyncTask(SplashActivity.this).execute("android", sharePreference.getDeviceId(), token, "push");
                        }

                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        SplashActivity.this.finish();
                    }
                }, SPLASH_TIME_OUT);

            }
        } else {
            android.app.FragmentManager manager = getFragmentManager();
            ConnectionAlertDialog dialog = new ConnectionAlertDialog();
            dialog.show(manager, "con_Dialog");
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(SplashActivity.this, SplashActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();

    }

}
