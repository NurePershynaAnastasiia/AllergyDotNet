package com.example.allergydotnet.util;

import java.sql.Blob;

public class DoctorInfo {

    private int doctor_id;

    private String doctor_name;

    private String doctor_info;

    private int doctor_price;





    public int getId() {
        return doctor_id;
    }

    public String getName() { return doctor_name; };

    public String getInfo() { return doctor_info; };

    public int getPrice() { return doctor_price; };


}
