package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.allergydotnet.util.DoctorInfo;
import com.example.allergydotnet.util.LoginInfo;
import com.example.allergydotnet.util.RetrofitInterface;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ChooseConsultTimeActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";

    Intent intent;
    int user_id, doctor_id;
    PaymentsClient paymentsClient;
    CalendarView calendarView;
    private TextView tvTime;
    private Handler handler;
    private Runnable runnable;
    RelativeLayout relLayout;

    TextView nameTextView;
    TextView infoTextView;

    TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_consult_time);

        intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);
        doctor_id = intent.getIntExtra("doctor_id", 0);


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

        retrofitInterface = retrofit.create(RetrofitInterface .class);


        HashMap<String, String> map = new HashMap<>();

        map.put("doctor_id", Integer.toString(doctor_id));

        Call<DoctorInfo> call1 = retrofitInterface.executeDoctorInfo(map);
        call1.enqueue(new Callback<DoctorInfo>() {
            @Override
            public void onResponse(Call<DoctorInfo> call, Response<DoctorInfo> response) {

                if (response.code() == 200) {

                    nameTextView = findViewById(R.id.nameTextView);
                    infoTextView = findViewById(R.id.infoTextView);
                    priceTextView = findViewById(R.id.priceTextView);

                    DoctorInfo result = response.body();

                    nameTextView.setText(result.getName());
                    infoTextView.setText(result.getInfo());
                    priceTextView.setText(Integer.toString(result.getPrice()) + "грн.");

                } else if (response.code() == 404) {
                    Toast.makeText(ChooseConsultTimeActivity.this, "Something went wrong" + Integer.toString(doctor_id),
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<DoctorInfo> call, Throwable t) {
                Toast.makeText(ChooseConsultTimeActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


//        Button paybtn = findViewById(R.id.paybtn);
//
//        Wallet.WalletOptions walletOptions = new Wallet.WalletOptions.Builder()
//                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
//                .build();
//        paymentsClient = Wallet.getPaymentsClient(this, walletOptions);
//
//        //IsReadyToPayRequest readyToPayRequest = IsReadyToPayRequest.fromJson(configurationJs)
//        paybtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        calendarView = findViewById(R.id.calendarView);
        //tvTime = findViewById(R.id.tv_time);
        relLayout = findViewById(R.id.mainLayout);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        };

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Snackbar snackbar = Snackbar.make(relLayout, "Selected Date: " + (month + 1) + "-" + dayOfMonth + "-" + year, Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                snackbar.show();
            }
        });
        ImageView paybtn = findViewById(R.id.paybtn);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        //tvTime.setText(currentTime);
    }



}