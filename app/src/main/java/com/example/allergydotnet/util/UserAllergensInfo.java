package com.example.allergydotnet.util;

import java.sql.Blob;

public class UserAllergensInfo {

    private int user_id;
    private String allergen_name;

    private String allergen_info;




    public int getId() {
        return user_id;
    }


    public String getAllergens() { return allergen_name; };

    public String getAllergensInfo() { return allergen_info; };


}
