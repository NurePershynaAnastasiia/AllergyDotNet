package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.UserNotationsInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

public class NotationsActivity extends AppCompatActivity {

    Intent intent;
    int user_id;

    LinearLayout scrollLayout;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notations);

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

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottom_navigation);
        bottomNavMenu.setSelectedItemId(R.id.invisible);
        bottomNavMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent myint;
                if (item.getItemId() == R.id.consultations) {
                    myint = new Intent(getApplicationContext(), ConsultationsActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                } else if (item.getItemId() == R.id.map) {
                    myint = new Intent(getApplicationContext(), MapActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                } else if (item.getItemId() == R.id.doctors) {
                    myint = new Intent(getApplicationContext(), DoctorsActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                }
                return false;
            }
        });

        Button add_notationBtn = findViewById(R.id.addbtn);
        add_notationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), NewNotationActivity.class);
                myint.putExtra("user_id", user_id);
                startActivity(myint);
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", Integer.toString(user_id));

        Call<ArrayList<UserNotationsInfo>> call1 = retrofitInterface.executeUserNotations(map);
        call1.enqueue(new Callback<ArrayList<UserNotationsInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserNotationsInfo>> call, Response<ArrayList<UserNotationsInfo>> response) {

                if (response.code() == 200) {

                    scrollLayout = findViewById(R.id.scroll_layout);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );


                    ArrayList<UserNotationsInfo> result = response.body();



                    for (int i = 0; i < result.size(); i++){
                        //date
                        TextView dateTextView = new TextView(NotationsActivity.this);
                        dateTextView.setText(result.get(i).getNote_dates().toString());
                        dateTextView.setTextSize(20);
                        dateTextView.setTextColor(Color.parseColor("#FFFFFF"));
                        dateTextView.setBackgroundColor(Color.parseColor("#B3CD98"));
                        //name
                        TextView nameTextView = new TextView(NotationsActivity.this);
                        nameTextView.setText(result.get(i).getNote_names().toString());
                        nameTextView.setTextSize(25);
                        //text
                        TextView textTextView = new TextView(NotationsActivity.this);
                        textTextView.setText(result.get(i).getNote_texts().toString());
                        textTextView.setTextSize(15);
                        dateTextView.setTextColor(Color.parseColor("#2E2E2E"));


                        scrollLayout.addView(dateTextView, layoutParams);
                        scrollLayout.addView(nameTextView, layoutParams);
                        scrollLayout.addView(textTextView, layoutParams);
                    }


                } else if (response.code() == 404) {
                    Toast.makeText(NotationsActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserNotationsInfo>> call, Throwable t) {
                Toast.makeText(NotationsActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}