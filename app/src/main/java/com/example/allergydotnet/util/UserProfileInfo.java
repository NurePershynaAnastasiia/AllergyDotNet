package com.example.allergydotnet.util;

public class UserProfileInfo {

    private int user_id;
    private String user_name;
    private boolean user_sub;
    private String[] allergens;

    private String[] note_names;
    private String[] note_texts;




    public int getId() {
        return user_id;
    }

    public String getName() {
        return user_name;
    }

    public boolean getSub() {
        return user_sub;
    }

    public String[] getAllergens() { return allergens; };

    public String[] getNote_names() { return note_names; };

    public String[] getNote_texts() { return note_texts; };
}
