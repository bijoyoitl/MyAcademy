package com.edu.myacademy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.edu.myacademy.R;
import com.edu.myacademy.Model.DrawerItem;


import java.util.ArrayList;

/**
 * Created by optimal on 24-Apr-16.
 */
public class DrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DrawerItem>  itemArrayList;
    private LayoutInflater layoutInflater;
    private LinearLayout dItemLayout;
 //   private Typeface typeface;

    public DrawerListAdapter(Context context, ArrayList<DrawerItem> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public int getCount() {
        return itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

      final ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = getLayoutInflater();
            view= inflater.inflate(R.layout.drawer_item,null);
            viewHolder=new ViewHolder();

            viewHolder.iconView=(ImageView)view.findViewById(R.id.drawerMenuIconImageView);
            viewHolder.menuName=(TextView)view.findViewById(R.id.menuNameTextView);

            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)view.getTag();
        }

        viewHolder.iconView.setImageResource(itemArrayList.get(i).getTitle_icon());
        viewHolder.menuName.setText(itemArrayList.get(i).getTitle());

        return view;
    }

    private int mSelectedPosition = -1;
    public void setSelectedItem (int itemPosition) {
        mSelectedPosition = itemPosition;
        notifyDataSetChanged();
    }
    public class ViewHolder{
        private ImageView iconView;
        private TextView menuName;

    }
    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }
}
