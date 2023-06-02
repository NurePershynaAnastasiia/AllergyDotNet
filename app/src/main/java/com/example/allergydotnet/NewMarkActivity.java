package com.example.allergydotnet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class NewMarkActivity extends AppCompatActivity {

    Intent intent;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mark);

        intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

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
    }
}