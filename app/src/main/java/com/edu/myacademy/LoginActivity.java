package com.edu.myacademy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.myacademy.Model.LoginInfo;
import com.edu.myacademy.Utils.LoginAsyncTask;
import com.edu.myacademy.Utils.MyAcademySharePreference;

public class LoginActivity extends AppCompatActivity {
    private Context context;
    private Button login_button;
    private Button registrationButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    private String user_id;
    private String password;
    private LoginInfo loginInfo;
    private CheckBox stayCheekBox;
    MyAcademySharePreference sharePreference;
    public static boolean status = false;

    TextView forgotPassTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.context = this;
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        login_button = (Button) findViewById(R.id.login_button);
        registrationButton = (Button) findViewById(R.id.registrationButton);
        stayCheekBox = (CheckBox) findViewById(R.id.stayCheekBox);
        forgotPassTV = (TextView) findViewById(R.id.forgotPassTV);

        sharePreference = new MyAcademySharePreference(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id = emailEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();
                status = stayCheekBox.isChecked();
                Log.e("LA", "status : " + status);
                if (user_id.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Email or Phone Number", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                } else {
                    loginInfo = new LoginInfo(user_id, password);
                    new LoginAsyncTask(context, status).execute(user_id, password, "login", "android", sharePreference.getDeviceId());
                }

            }
        });


        forgotPassTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = 1;
                String title = "Retrieve Password";
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
