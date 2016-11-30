package com.edu.myacademy;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.edu.myacademy.Utils.MyAcademySharePreference;

/**
 * Created by ripon on 11/8/2016.
 */

public class MyInstanceId extends FirebaseInstanceIdService {
    MyAcademySharePreference preference;

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("MyInstance", "token : " + token);
        preference = new MyAcademySharePreference(this);
        preference.saveToken(token);
        super.onTokenRefresh();
    }

}
