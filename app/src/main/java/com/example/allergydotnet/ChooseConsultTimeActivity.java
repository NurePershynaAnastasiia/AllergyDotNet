package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChooseConsultTimeActivity extends AppCompatActivity {

    Intent intent;
    int user_id, doctor_id;
    PaymentsClient paymentsClient;
    CalendarView calendarView;
    private TextView tvTime;
    private Handler handler;
    private Runnable runnable;
    LinearLayout linearLayout;
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
        linearLayout = findViewById(R.id.layout);

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

                Snackbar snackbar = Snackbar.make(linearLayout, "Selected Date: " + (month + 1) + "-" + dayOfMonth + "-" + year, Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                snackbar.show();
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