package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottom_navigation);
        bottomNavMenu.setSelectedItemId(R.id.invisible);
        bottomNavMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent myint;
                if (item.getItemId() == R.id.consultations) {
                    myint = new Intent(getApplicationContext(), ConsultationsActivity.class);
                    startActivity(myint);
                } else if (item.getItemId() == R.id.map) {
                    myint = new Intent(getApplicationContext(), MapActivity.class);
                    startActivity(myint);
                } else if (item.getItemId() == R.id.doctors) {
                    myint = new Intent(getApplicationContext(), DoctorsActivity.class);
                    startActivity(myint);
                }
                return false;
            }
        });

        ImageButton settingsBtn = findViewById(R.id.settingsbtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(myint);
            }
        });


    }
}