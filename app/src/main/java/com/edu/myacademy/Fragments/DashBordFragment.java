package com.edu.myacademy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.edu.myacademy.Adapter.DashBordAdapter;
import com.edu.myacademy.HomeActivity;
import com.edu.myacademy.Model.DrawerItem;
import com.edu.myacademy.Utils.MyAcademySharePreference;
import com.edu.myacademy.R;

import java.util.ArrayList;


public class DashBordFragment extends Fragment {
    GridView dashBordGridView;
    String menuItem[];
    ArrayList<DrawerItem> dashItemArrayList;
    DashBordAdapter dashBordAdapter;
    DrawerItem drawerItem;
    TextView nameTextView;
    MyAcademySharePreference sharePreference;
    FragmentManager fragmentManager;


    final Integer[] drawerItem_icon = {R.drawable.admission_preparation, R.drawable.study_table, R.drawable.digital_book, R.drawable.mode_test,
            R.drawable.quiz, R.drawable.video_document, R.drawable.campus,R.drawable.general_knowledge, R.drawable.bcs,
            R.drawable.army, R.drawable.nevy, R.drawable.air_force, R.drawable.issb, R.drawable.child_corner,
            R.drawable.university_admission_info, R.drawable.edu_jobs_info, R.drawable.opinion, R.drawable.gallery, R.drawable.team};


    public DashBordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_bord, container, false);
        dashBordGridView = (GridView) view.findViewById(R.id.dashBordGridView);
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);

        sharePreference = new MyAcademySharePreference(getActivity());

        nameTextView.setText(sharePreference.getUserName()+getString(R.string.dashboard));
        dashItemArrayList = new ArrayList<>();
        menuItem =  getResources().getStringArray(R.array.dashbord_item);

        for (int i = 0; i < menuItem.length; i++) {
            drawerItem = new DrawerItem(drawerItem_icon[i], menuItem[i]);
            dashItemArrayList.add(drawerItem);
        }

        dashBordAdapter = new DashBordAdapter(getActivity(), dashItemArrayList);
        dashBordGridView.setAdapter(dashBordAdapter);


        dashBordGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HomeActivity.titleTextView.setText(dashItemArrayList.get(i).getTitle());
                displayView(i);
            }
        });

        return view;
    }

    public void displayView(int i) {

        Fragment fragment;
        int featureId = 0;

        switch (i) {
            case 0:
                featureId = 1;  //addmission_pre
                break;
            case 1:
                featureId = 2;  //class_wise_study
                break;
            case 2:
                featureId = 3;  //digital_book
                break;
            case 3:
                featureId = 4;  //model_test
                break;
            case 4:
                featureId = 5;  //quize
                break;
            case 5:
                featureId = 6;  //vedio_doc
                break;
            case 6:
                featureId = 7;  //campus
                break;
            case 7:
                featureId = 8;  //g_knowledge
                break;
            case 8:
                featureId = 9;      //bcs
                break;
            case 9:
                featureId = 10;     //army
                break;
            case 10:
                featureId = 11;     //navy
                break;
            case 11:
                featureId = 12;     //air_force
                break;
            case 12:
                featureId = 13;     //issb
                break;
            case 13:
                featureId = 14;     //child_corner
                break;
            case 14:
                featureId = 15;     //university_add_info
                break;
            case 15:
                featureId = 16;     //edu_job_info
                break;
            case 16:
                featureId = 17;     //opinion
                break;
            case 17:
                featureId = 18;     //gallery
                break;
            case 18:
                featureId=19;       //team
                break;
            default:
                break;
        }


        fragment = new WebViewFragment(featureId);

        if (featureId != 0 ) {
            if (fragment != null) {
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentF, fragment).commit();

            }
        }

    }


}
