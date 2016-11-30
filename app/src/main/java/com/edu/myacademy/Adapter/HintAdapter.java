package com.edu.myacademy.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Bijoy on 10/18/2016.
 */

public class HintAdapter extends ArrayAdapter<Object> {

    private Context m_cContext;
    public HintAdapter(Context context,String[] objects,int textViewResourceId) {
        super(context, textViewResourceId, objects);
        this.m_cContext = context;
    }


    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
