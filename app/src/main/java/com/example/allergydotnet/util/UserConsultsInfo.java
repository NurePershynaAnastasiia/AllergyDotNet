package com.example.allergydotnet.util;

import java.sql.Blob;

public class UserConsultsInfo {

    private int consultation_id;
    private String consultation_date;
    private String consultation_status;
    private String doctor_name;



    public int getConsultation_id() {
        return consultation_id;
    }

    public String getConsultation_date() {
        return consultation_date;
    }

    public String getConsultation_status() {
        return consultation_status;
    }

    public String getDoctor_name() { return doctor_name; }

}
