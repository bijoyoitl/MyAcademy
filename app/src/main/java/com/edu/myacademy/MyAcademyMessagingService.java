package com.edu.myacademy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.edu.myacademy.HomeActivity;
import com.edu.myacademy.LoginActivity;
import com.edu.myacademy.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.edu.myacademy.Utils.MyAcademySharePreference;

/**
 * Created by ripon on 11/9/2016.
 */

public class MyAcademyMessagingService extends FirebaseMessagingService {
    MyAcademySharePreference preference;
    Intent intent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String msg = remoteMessage.getNotification().getBody();
        Log.e("MMS", "msg : " + msg);
        preference = new MyAcademySharePreference(this);

        boolean status = preference.getStatus();

        if (status == true) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText(msg);
        builder.setSmallIcon(R.mipmap.app_logo);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

        super.onMessageReceived(remoteMessage);
    }
}
