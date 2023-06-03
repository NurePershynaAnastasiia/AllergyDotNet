package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.internal.Internal;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allergydotnet.util.DoctorInfo;
import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.UserNotationsInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorsActivity extends AppCompatActivity {

    Intent intent;
    int user_id;

    LinearLayout scrollLayout;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottom_navigation);
        bottomNavMenu.setSelectedItemId(R.id.doctors);

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


        Call<ArrayList<DoctorInfo>> call1 = retrofitInterface.executeDoctorsInfo();
        call1.enqueue(new Callback<ArrayList<DoctorInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<DoctorInfo>> call, Response<ArrayList<DoctorInfo>> response) {

                if (response.code() == 200) {

                    scrollLayout = findViewById(R.id.scroll_layout);

                    ArrayList<DoctorInfo> result = response.body();


                    for (int i = 0; i < result.size(); i++){

                        int doctor_id = result.get(i).getId();

                        //name
                        TextView nameTextView = new TextView(DoctorsActivity.this);
                        nameTextView.setText(result.get(i).getName());
                        nameTextView.setTextSize(23);
                        //info
                        TextView infoTextView = new TextView(DoctorsActivity.this);
                        infoTextView.setText(result.get(i).getInfo());
                        infoTextView.setTextSize(15);
                        infoTextView.setTextColor(Color.parseColor("#2E2E2E"));
                        //price
                        TextView priceTextView = new TextView(DoctorsActivity.this);
                        priceTextView.setText(Integer.toString(result.get(i).getPrice()) + "грн.");
                        priceTextView.setTextSize(20);
                        priceTextView.setTextColor(Color.parseColor("#407A61"));
                        //image
                        //ImageView photoImageView = new ImageView(DoctorsActivity.this);
                        //photoImageView.setBackgroundResource(R.drawable.doctor_image);
                        //photoImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        //photoImageView.setAdjustViewBounds(true);

                        Button btn = new Button(DoctorsActivity.this);
                        btn.setText("Обрати час");
                        btn.setTextColor(Color.parseColor("#FFFFFF"));
                        btn.setBackgroundColor(Color.parseColor("#305E4A"));
                        btn.setWidth(200);

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent myint = new Intent(getApplicationContext(), ChooseConsultTimeActivity.class);
                                myint.putExtra("user_id", user_id);
                                myint.putExtra("doctor_id", doctor_id);
                                startActivity(myint);
                            }
                        });

                        //scrollLayout.addView(photoImageView);
                        scrollLayout.addView(nameTextView);
                        scrollLayout.addView(priceTextView);
                        scrollLayout.addView(infoTextView);
                        scrollLayout.addView(btn);
                    }

                } else if (response.code() == 404) {
                    Toast.makeText(DoctorsActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DoctorInfo>> call, Throwable t) {
                Toast.makeText(DoctorsActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}