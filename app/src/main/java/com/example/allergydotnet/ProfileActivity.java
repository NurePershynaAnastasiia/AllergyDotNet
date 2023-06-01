package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.example.allergydotnet.util.LoginInfo;
import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.UserProfileInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    RelativeLayout layout;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://172.20.10.2:3000";

    private TextView nameTextView = findViewById(R.id.name);
    private TextView sub_typeTextView = findViewById(R.id.subscrtype);

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

        Button recommendBtn = findViewById(R.id.recommendationsbtn);
        recommendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), RecommendationsActivity.class);
                startActivity(myint);
            }
        });

        RelativeLayout subscription_layout = findViewById(R.id.layout1_profile);
        layout = findViewById(R.id.profile_relative);
        subscription_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSubscriptionPopup();
            }
        });
    }

    private void CreateSubscriptionPopup() {
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View[] popUpView = {inflater.inflate(R.layout.popup_subscription, null)};

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView[0],width,height,focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);
            }
        });

        Button standartButton = popUpView[0].findViewById(R.id.standartbtn);
        Button premiumButton = popUpView[0].findViewById(R.id.premiumbtn);

        standartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                View newPopupView = inflater.inflate(R.layout.popup_standart, null);
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                boolean focusable = true;
                PopupWindow popupWindow = new PopupWindow(newPopupView,width,height,focusable);
                layout.post(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);
                    }
                });
                Button backButton = newPopupView.findViewById(R.id.backbtn);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                newPopupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        premiumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                View newPopupView = inflater.inflate(R.layout.popup_premium, null);
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                boolean focusable = true;
                PopupWindow popupWindow = new PopupWindow(newPopupView,width,height,focusable);
                layout.post(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);
                    }
                });
                Button payButton = newPopupView.findViewById(R.id.paybtn);
                payButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        popUpView[0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        Intent intent = getIntent();
        int user_id = intent.getIntExtra("user_id", 0);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", Integer.toString(user_id));

        Call<UserProfileInfo> call = retrofitInterface.executeProfile(map);
        call.enqueue(new Callback<UserProfileInfo>() {
            @Override
            public void onResponse(Call<UserProfileInfo> call, Response<UserProfileInfo> response) {


                if (response.code() == 200) {

                    UserProfileInfo result = response.body();
                    String user_name = result.getName();
                    String user_sub = (result.getSub()? "Преміум" : "Стандартна");
                    
                    nameTextView.setText(user_name);
                    sub_typeTextView.setText(user_sub);

                    //AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    //builder1.setTitle(result.getName());
                    //builder1.setMessage(result.getEmail());
                    //builder1.show();


                } else if (response.code() == 404) {
                    Toast.makeText(ProfileActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileInfo> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}