package com.example.allergydotnet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        val btmNav = findViewById<BottomNavigationView>(R.id.bottom_navigation);
        btmNav.itemIconTintList = null;
    }
}