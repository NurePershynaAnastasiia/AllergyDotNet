package com.example.allergydotnet.util;

import android.widget.ImageView;

public class PointInfo {
//    CREATE TABLE Points (
//            point_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
//            point_name TEXT NOT NULL,
//            point_photo BLOB,
//            point_info TEXT NOT NULL,
//            point_status BOOLEAN NOT NULL,
//            point_coordinates_latitude TEXT NOT NULL,
//            point_coordinates_longitude TEXT NOT NULL,
//            allergen_id INTEGER NOT NULL REFERENCES Allergens(allergen_id) ON DELETE CASCADE
//);

//    const query = 'SELECT a.allergen_name, a.allergen_photo, p.point_coordinates_latitude, p.point_coordinates_longitude\n' +
//            'FROM Users u\n' +
//            'JOIN UserAllergens ua ON u.user_id = ua.user_id\n' +
//            'JOIN Allergens a ON ua.allergen_id = a.allergen_id\n' +
//            'JOIN Points p ON a.allergen_id = p.allergen_id WHERE u.user_id = ? AND p.point_status = \'1\'';
//    private point_id
    private int point_id;
    private String allergen_name;
    private ImageView point_photo;
    private String point_coordinates_latitude;
    private String point_coordinates_longitude;

}
