package com.edu.myacademy;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.edu.myacademy.Model.RegisterInfo;
import com.edu.myacademy.Utils.MyAcademySharePreference;
import com.edu.myacademy.Utils.RegistrationAsyncTask;

public class RegistrationActivity extends AppCompatActivity {

    private Context context;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Spinner classTypeSpinner;
    private Spinner userTypeSpinner;
    private CheckBox termsConditionCheekBox;
    private Button reg_button;

    private String name;
    private String email;
    public int userTypeValue = 0;
    int classTypeValue = 0;
    private String password;
    private String con_password;

    private RegisterInfo registerInfo;
    private MyAcademySharePreference sharePreference;


    String[] userType = {"Select user type", "Student", "Teacher", "General User"};
    String[] classType = {"Select Class", "পঞ্চম (V)", " নবম - দশম (IX - X)", " একাদশ - দ্বাদশ (XI - XII)", " অন্যান্য (Other)", "  ভর্তি প্রস্তুতি (Admission Preparation)", " আই এস এস বি (ISSB) ", "  বি সি এস প্রিলিমিনারি (BCS Preliminary)", "  Vocabulary (18+) (Vocabulary (18+))", " আর্মি (সেনাবাহিনী (Army))", " নেভি (নৌবাহিনী (Navy))", " এয়ারফোর্স (বিমানবাহিনী (Air Force))"};

    /*"পঞ্চম (V) = 2", " নবম - দশম (IX - X)= 6", " একাদশ - দ্বাদশ (XI - XII)=7 ", " অন্যান্য (Other)=14","  ভর্তি প্রস্তুতি (Admission Preparation)=9"," আই এস এস বি (ISSB) =12","  বি সি এস প্রিলিমিনারি (BCS Preliminary)=13","  Vocabulary (18+) (Vocabulary (18+))=15"," আর্মি (সেনাবাহিনী (Army))=16"," নেভি (নৌবাহিনী (Navy))=17"," এয়ারফোর্স (বিমানবাহিনী (Air Force))=18", "-Select Class-".*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.context = this;
        idReference();
        sharePreference = new MyAcademySharePreference(this);
        userandclassTypeSpinnerShow();

        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        userTypeValue = 0;

                        break;
                    case 1:
                        userTypeValue = 1;

                        break;
                    case 2:
                        userTypeValue = 2;

                        break;
                    case 3:
                        userTypeValue = 3;
                        break;
                    default:
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        classTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        classTypeValue = 0;
                        break;
                    case 1:
                        classTypeValue = 2;
                        break;
                    case 2:
                        classTypeValue = 6;
                        break;
                    case 3:
                        classTypeValue = 7;
                        break;
                    case 4:
                        classTypeValue = 14;
                        break;
                    case 5:
                        classTypeValue = 9;
                        break;
                    case 6:
                        classTypeValue = 12;
                        break;
                    case 7:
                        classTypeValue = 13;
                        break;
                    case 8:
                        classTypeValue = 15;
                        break;
                    case 9:
                        classTypeValue = 16;
                        break;
                    case 10:
                        classTypeValue = 17;
                        break;
                    case 11:
                        classTypeValue = 18;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void userandclassTypeSpinnerShow() {

        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userType);
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(userTypeAdapter);

        ArrayAdapter<String> classTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, classType);
        classTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classTypeSpinner.setAdapter(classTypeAdapter);

    }

    private void idReference() {
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);

        classTypeSpinner = (Spinner) findViewById(R.id.classTypeSpinner);
        userTypeSpinner = (Spinner) findViewById(R.id.userTypeSpinner);

        termsConditionCheekBox = (CheckBox) findViewById(R.id.termConditionCheekBox);
        reg_button = (Button) findViewById(R.id.reg_button);
    }

    public void reg_button(View view) {
        name = nameEditText.getText().toString().trim();

        email = emailEditText.getText().toString().trim();

        password = passwordEditText.getText().toString().trim();

        con_password = confirmPasswordEditText.getText().toString().trim();

        if (name.equals("")) {
            Toast.makeText(context, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        } else if (email.equals("")) {
            Toast.makeText(context, "Please Enter a Valid Email or Mobile Number", Toast.LENGTH_SHORT).show();
        } else if (userTypeValue == 0) {
            Toast.makeText(context, "Please Select User Type", Toast.LENGTH_SHORT).show();
        } else if (classTypeValue == 0) {
            Toast.makeText(context, "Please Select Your Class", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(context, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
        } else if (con_password.equals("")) {
            Toast.makeText(context, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();
        } else if (!termsConditionCheekBox.isChecked()) {
            Toast.makeText(context, "Term and Condition is not Checked", Toast.LENGTH_SHORT).show();
        } else {
            if (password.equals(con_password)) {
                registerInfo = new RegisterInfo(name, email, userTypeValue + "", classTypeValue + "", password, con_password);
                new RegistrationAsyncTask(context).execute(registerInfo.getName(), registerInfo.getEmail(), registerInfo.getUserType(), registerInfo.getClassType(), registerInfo.getPassword(), registerInfo.getCon_password(), "registration", "android", sharePreference.getDeviceId());
            } else {
                Toast.makeText(context, "PassWord Not Matching", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
