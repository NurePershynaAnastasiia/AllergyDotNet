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
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.UserAllergensInfo;
import com.example.allergydotnet.util.UserConsultsInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsultationsActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";

    Intent intent;
    int user_id;

    LinearLayout scrollLayout;

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultations);

        layout = findViewById(R.id.consLayout);
        intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottom_navigation);
        bottomNavMenu.setSelectedItemId(R.id.consultations);

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

        Call<ArrayList<UserConsultsInfo>> call = retrofitInterface.executeLoadUserConsultationsn(map);



        call.enqueue(new Callback<ArrayList<UserConsultsInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserConsultsInfo>> call, Response<ArrayList<UserConsultsInfo>> response) {

                if (response.code() == 200) {

                    scrollLayout = findViewById(R.id.scroll_layout);
                    ArrayList<UserConsultsInfo> result = response.body();



                    for (int i = 0; i < result.size(); i++){

                        int consult_id = result.get(i).getConsultation_id();
                        //name
                        TextView nameTextView = new TextView(ConsultationsActivity.this);
                        nameTextView.setTextColor(Color.parseColor("#FFFFFF"));
                        nameTextView.setText(result.get(i).getDoctor_name().toString());
                        nameTextView.setBackgroundColor(Color.parseColor("#B3CD98"));
                        nameTextView.setTextSize(25);
                        //date
                        TextView dateTextView = new TextView(ConsultationsActivity.this);
                        dateTextView.setText(result.get(i).getConsultation_date().toString());
                        nameTextView.setTextColor(Color.parseColor("#000000"));
                        dateTextView.setTextSize(20);
                        //status
                        Button statusButton = new Button(ConsultationsActivity.this);
                        String btnText = result.get(i).getConsultation_status().toString();
                        statusButton.setText(btnText);
                        if (btnText.equals("Очікується"))
                            statusButton.setBackgroundColor(Color.parseColor("#407A61"));
                        if (btnText.equals("Скасовано"))
                            statusButton.setBackgroundColor(Color.parseColor("#B13434"));
                        if (btnText.equals("Завершено"))
                            statusButton.setBackgroundColor(Color.parseColor("#D5D8DE"));

                        statusButton.setLayoutParams(new LinearLayout.LayoutParams(400, 100));

                        //cancel
                        Button cancelButton = new Button(ConsultationsActivity.this);
                        cancelButton.setText("Скасувати");
                        cancelButton.setBackgroundColor(Color.parseColor("#B13434"));
                        cancelButton.setLayoutParams(new LinearLayout.LayoutParams(400, 100));
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CreateDeclineConsultPopup(consult_id);
                            }
                        });



                        LinearLayout linearLayout = new LinearLayout(ConsultationsActivity.this);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                        scrollLayout.addView(nameTextView);
                        scrollLayout.addView(dateTextView);
                        scrollLayout.addView(linearLayout);
                        linearLayout.addView(statusButton);
                        if (btnText.equals("Очікується")){
                            TextView tmpTextView = new TextView(ConsultationsActivity.this);
                            tmpTextView.setText("        ");
                            linearLayout.addView(tmpTextView);
                            linearLayout.addView(cancelButton);
                        }

                        scrollLayout.addView(new TextView(ConsultationsActivity.this));
                        scrollLayout.addView(new TextView(ConsultationsActivity.this));
                    }



                } else if (response.code() == 404) {
                    Toast.makeText(ConsultationsActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<UserConsultsInfo>> call, Throwable t) {
                Toast.makeText(ConsultationsActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    private void CreateDeclineConsultPopup(int consult_id) {
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView=inflater.inflate(R.layout.popup_decline_consult,null);
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
        Button SendButton = popUpView.findViewById(R.id.sendbtn);
        CheckBox checkBox = popUpView.findViewById(R.id.checkbox);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){

                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    retrofitInterface = retrofit.create(RetrofitInterface.class);


                    HashMap<String, String> map = new HashMap<>();

                    map.put("consultation_id", Integer.toString(consult_id));

                    Call<UserConsultsInfo> call = retrofitInterface.executeCancelCons(map);

                    call.enqueue(new Callback<UserConsultsInfo>() {
                        @Override
                        public void onResponse(Call<UserConsultsInfo> call, Response<UserConsultsInfo> response) {

                            if (response.code() == 200) {
                                Toast.makeText(ConsultationsActivity.this, "Cool",
                                        Toast.LENGTH_LONG).show();

                            } else if (response.code() == 404) {
                                Toast.makeText(ConsultationsActivity.this, "Something went wrong" + Integer.toString(consult_id) ,
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<UserConsultsInfo> call, Throwable t) {
                            Toast.makeText(ConsultationsActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                    Intent myint = new Intent(getApplicationContext(), ConsultationsActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);

                    popupWindow.dismiss();
                }

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