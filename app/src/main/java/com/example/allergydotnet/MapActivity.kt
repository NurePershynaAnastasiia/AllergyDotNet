package com.example.allergydotnet

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        val btmNav = findViewById<BottomNavigationView>(R.id.bottom_navigation);
        btmNav.itemIconTintList = null;

        var avatar_icon = findViewById(R.id.avatar_icon) as ImageView;
        /*
        avatar_icon.setOnClickListener(new setOnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapActivity.this, "you clicked it", Toast.LENGTH_SHORT).show();
            }
        })*/
    }
}