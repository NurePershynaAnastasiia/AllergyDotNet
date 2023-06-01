package com.example.allergydotnet.util;

public class SignupInfo {
    private int user_id;
    private int user_name;

    private String user_email;

    private String user_password;

    public String getPassword() {
        return user_password;
    }

    public String getEmail() {
        return user_email;
    }

    public int getId() {
        return user_id;
    }

    public int getUserName() {
        return user_name;
    }
}
