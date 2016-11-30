package com.edu.myacademy.Model;

/**
 * Created by Bijoy on 10/19/2016.
 */

public class RegisterInfo {
    private String name;
    private String email;
    private String userType;
    private String classType;
    private String password;
    private String con_password;
//
    public RegisterInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public RegisterInfo(String name, String email, String userType, String classType, String password, String con_password) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.classType = classType;
        this.password = password;
        this.con_password = con_password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public String getClassType() {
        return classType;
    }

    public String getPassword() {
        return password;
    }

    public String getCon_password() {
        return con_password;
    }
}
