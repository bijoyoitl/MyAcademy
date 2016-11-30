package com.edu.myacademy.Model;

/**
 * Created by Bijoy on 10/22/2016.
 */

public class DrawerItem {
    private int title_icon;
    private String title;

    public DrawerItem(int title_icon, String title) {
        this.title_icon = title_icon;
        this.title = title;
    }

    public int getTitle_icon() {
        return title_icon;
    }

    public String getTitle() {
        return title;
    }
}
