package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class SettingsActivity extends AppCompatActivity {
    RelativeLayout layout;
    Intent intent;
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottom_navigation);
        bottomNavMenu.setSelectedItemId(R.id.invisible);
        bottomNavMenu.setOnItemReselectedListener(new BottomNavigationView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Intent myint;
                if (item.getItemId() == R.id.consultations){
                    myint = new Intent(getApplicationContext(), ConsultationsActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                }
                else if (item.getItemId() == R.id.map){
                    myint = new Intent(getApplicationContext(), MapActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                }
                else if (item.getItemId() == R.id.doctors){
                    myint = new Intent(getApplicationContext(), DoctorsActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                }
                else {
                    bottomNavMenu.setSelectedItemId(R.id.invisible);
                }
            }
        });

        Button quitBtn = findViewById(R.id.quitbtn);
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), LoginActivity.class);
                myint.putExtra("user_id", user_id);
                startActivity(myint);
            }
        });

        RelativeLayout top_menu = findViewById(R.id.top_menu);
        ImageButton toProfile = findViewById(R.id.profilebtn);
        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                myint.putExtra("user_id", user_id);
                startActivity(myint);
            }
        });

        TextView writeToSupport = findViewById(R.id.supportLetter);
        layout = findViewById(R.id.settings_relative);
        writeToSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 CreateTechSupportPopup();
            }
        });
    }

    private void CreateTechSupportPopup() {
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView=inflater.inflate(R.layout.popup_tech_support,null);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView,width,height,focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);

            }
        });
        TextView SendButton;
        SendButton = popUpView.findViewById(R.id.sendbtn);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}