package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.UserAllergensInfo;
import com.example.allergydotnet.util.UserNotationsInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class RecommendationsActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";

    Intent intent;
    int user_id;

    LinearLayout scrollLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

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

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        retrofitInterface = retrofit.create(RetrofitInterface.class);


        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", Integer.toString(user_id));

        Call<ArrayList<UserAllergensInfo>> call = retrofitInterface.executeUserAllergens(map);


        call.enqueue(new Callback<ArrayList<UserAllergensInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserAllergensInfo>> call, Response<ArrayList<UserAllergensInfo>> response) {

                if (response.code() == 200) {

                    scrollLayout = findViewById(R.id.scroll_layout);
                    ArrayList<UserAllergensInfo> result = response.body();


                    for (int i = 0; i < result.size(); i++){


                        //name
                        TextView nameTextView = new TextView(RecommendationsActivity.this);
                        nameTextView.setText(result.get(i).getAllergens().toString());
                        nameTextView.setTextColor(Color.parseColor("#000000"));
                        nameTextView.setBackgroundColor(Color.parseColor("#D5D8DE"));
                        nameTextView.setTextSize(20);
                        //info
                        TextView infoTextView = new TextView(RecommendationsActivity.this);
                        infoTextView.setText(result.get(i).getAllergensInfo().toString());
                        infoTextView.setTextColor(Color.parseColor("#2E2E2E"));
                        infoTextView.setTextSize(15);


                        scrollLayout.addView(nameTextView);
                        scrollLayout.addView(infoTextView);
                        scrollLayout.addView(new TextView(RecommendationsActivity.this));
                        //scrollLayout.addView(imageView);

                    }



                } else if (response.code() == 404) {
                    Toast.makeText(RecommendationsActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserAllergensInfo>> call, Throwable t) {
                Toast.makeText(RecommendationsActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });




    }
}