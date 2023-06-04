package com.example.allergydotnet.util;

import com.google.android.gms.maps.model.Marker;

import java.io.Serializable;

public class MarkerInfo implements Serializable {

    private int id;
    private double longtitude;
    private double latitude;
    private String title;
    private String description;
    private String hash;


    public MarkerInfo(int id, double longtitude, double latitude, String title, String description, String hash) {
        this.id = id;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.title = title;
        this.description = description;
        this.hash = hash;
    }

    public MarkerInfo(int id, double longtitude, double latitude, String title) {
        this.id = id;
        this.title = title;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHash() {
        return hash;
    }
}
