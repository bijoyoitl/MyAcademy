package com.edu.myacademy;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.myacademy.Adapter.DrawerListAdapter;
import com.edu.myacademy.DialogFragments.ExitDialog;
import com.edu.myacademy.Fragments.DashBordFragment;
import com.edu.myacademy.Fragments.WebViewFragment;
import com.edu.myacademy.Model.DrawerItem;
import com.edu.myacademy.Utils.BaseUrl;
import com.edu.myacademy.Utils.MyAcademySharePreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private Context context;
    private Toolbar toolbar;
    private ImageView profileImageView;
    public static TextView titleTextView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private ListView drawerListView;
    private ArrayList<DrawerItem> drawerItemArrayList;
    private DrawerItem drawerItems;
    private DrawerListAdapter drawerListAdapter;
    private FragmentTransaction fragmentTransaction;
    android.support.v4.app.FragmentManager fragmentManager;
    public MyAcademySharePreference sharePreference;
    public static String user_name;
    public static String user_id;
    public static String image_url;
    String drawerItem[];
    int featureId = 0;
    Fragment fragment;
    FrameLayout contentF;
    int backpress;
    int count = 0;

    final Integer[] drawerItem_icon = {R.drawable.left_dashboard, R.drawable.left_admission_preparation, R.drawable.left_study_table, R.drawable.left_digital_book, R.drawable.left_mode_test,
            R.drawable.left_quiz, R.drawable.left_video_document, R.drawable.left_campus, R.drawable.left_general_knowledge, R.drawable.left_bcs,
            R.drawable.left_army, R.drawable.left_nevy, R.drawable.left_air_force, R.drawable.left_issb, R.drawable.left_child_corner,
            R.drawable.left_university_admission_info, R.drawable.left_edu_jobs_info, R.drawable.left_opinion, R.drawable.left_gallery, R.drawable.left_team};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.context = this;
        sharePreference = new MyAcademySharePreference(this);

        referenceId();
        drawerItemArrayList = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        user_id = sharePreference.getUserId();
        user_name = sharePreference.getUserName();
        image_url = BaseUrl.base_url + sharePreference.getUserImageUrl();
        Log.e("HA", "url : " + image_url);
        if (!image_url.equals("")) {
            Picasso.with(context).load(image_url).error(R.drawable.main_profile_icon).into(profileImageView);
        }


        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.inflate(R.menu.menu_main);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.profile) {

                            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("title", "Profile");
                            intent.putExtra("id", 2);

                            startActivity(intent);
                        } else if (id == R.id.logout) {

                            logout();
                        }

                        return true;
                    }
                });


            }
        });

        for (int i = 0; i < drawerItem.length; i++) {
            drawerItems = new DrawerItem(drawerItem_icon[i], drawerItem[i]);
            drawerItemArrayList.add(drawerItems);
        }

        drawerListAdapter = new DrawerListAdapter(context, drawerItemArrayList);
        drawerListView.setAdapter(drawerListAdapter);
        drawerListView.setOnItemClickListener(new SlideMenuClickListener());

        if (savedInstanceState == null) {
            displayView(0);
        }

    }

    private void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myAcademyShare", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }

    public class SlideMenuClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            titleTextView.setText(drawerItemArrayList.get(i).getTitle());
            displayView(i);

        }
    }

    public void displayView(int i) {


        Fragment fragment2 = null;


        switch (i) {
            case 0:
                titleTextView.setText(R.string.app_name);
                fragment2 = new DashBordFragment();
                backpress = 0;
                break;
            case 1:
                featureId = 1;  //addmission_pre
                backpress = (backpress + 1);
                break;
            case 2:
                featureId = 2;  //class_wise_study
                backpress = (backpress + 1);
                break;
            case 3:
                featureId = 3;  //digital_book
                backpress = (backpress + 1);
                break;
            case 4:
                featureId = 4;  //model_test
                backpress = (backpress + 1);
                break;
            case 5:
                featureId = 5;  //quize
                backpress = (backpress + 1);
                break;
            case 6:
                featureId = 6;  //vedio_doc
                backpress = (backpress + 1);
                break;
            case 7:
                featureId = 7;  //campus
                backpress = (backpress + 1);
                break;
            case 8:
                featureId = 8;  //g_knowledge
                backpress = (backpress + 1);
                break;
            case 9:
                featureId = 9;      //bcs
                backpress = (backpress + 1);
                break;
            case 10:
                featureId = 10;     //army
                backpress = (backpress + 1);
                break;
            case 11:
                featureId = 11;     //navy
                backpress = (backpress + 1);
                break;
            case 12:
                featureId = 12;     //air_force
                backpress = (backpress + 1);
                break;
            case 13:
                featureId = 13;     //issb
                backpress = (backpress + 1);
                break;
            case 14:
                featureId = 14;     //child_corner
                backpress = (backpress + 1);
                break;
            case 15:
                featureId = 15;     //university_add_info
                backpress = (backpress + 1);
                break;
            case 16:
                featureId = 16;     //edu_job_info
                backpress = (backpress + 1);
                break;
            case 17:
                featureId = 17;     //opinion
                backpress = (backpress + 1);
                break;
            case 18:
                featureId = 18;     //gallery
                backpress = (backpress + 1);
                break;
            case 19:
                featureId = 19;       //team
                backpress = (backpress + 1);
                break;
            default:
                break;
        }


        fragment = new WebViewFragment(featureId);

        if (featureId != 0) {
            if (fragment != null) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentF, fragment).commit();

                drawerLayout.closeDrawer(navigationView);
            } else {
                drawerLayout.closeDrawer(navigationView);
            }
        }

        if (fragment2 != null) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contentF, fragment2).commit();

            drawerLayout.closeDrawer(navigationView);
        } else {
            drawerLayout.closeDrawer(navigationView);
        }

    }


    private void referenceId() {
        contentF = (FrameLayout) findViewById(R.id.contentF);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerListView = (ListView) findViewById(R.id.drawerListView);
        drawerItem = getResources().getStringArray(R.array.drawer_item);
    }

/*    @Override
    public void onBackPressed() {
//        int count = getFragmentManager().getBackStackEntryCount();
//        Log.e("MA"," back count : "+count);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        }
        else {
            android.app.FragmentManager manager = getFragmentManager();
            ExitDialog dialog = new ExitDialog();
            dialog.show(manager, "Exit_Dialog");
//            if (backpress>=1){
//                Fragment fragment2 = new DashBordFragment();
//                if (fragment2 != null) {
//                    fragmentManager = getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.contentF, fragment2).commit();
//                    titleTextView.setText(R.string.app_name);
//                }
//            }
        }
    }*/

//    @Override
//    protected void onPause() {
//        boolean status = sharePreference.getStatus();
//        if (!status) {
//            logout();
//        }
//        super.onPause();
//    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (count == 1) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                        count = count - 1;
                    } else {
                        count = 1;
                    }
                } else if (count == 2) {
                    Fragment fragment2 = new DashBordFragment();
                    if (fragment2 != null) {
                        fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.contentF, fragment2).commit();
                        titleTextView.setText(R.string.app_name);
                    }
                    count=count-1;

                }else if (count==0){
                    android.app.FragmentManager manager = getFragmentManager();
                    ExitDialog dialog = new ExitDialog();
                    dialog.show(manager, "Exit_Dialog");
                }

                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
