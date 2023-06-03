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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allergydotnet.util.AllergensNamesInfo;
import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.UserAllergensInfo;
import com.example.allergydotnet.util.UserNameSubInfo;
import com.example.allergydotnet.util.UserNotationsInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";

    private RelativeLayout layout;
    String user_sub;

    private TextView nameTextView;
    private TextView sub_typeTextView;

    Spinner spinner;

    private TextView all_notaionsTextView;

    Intent intent;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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



        ImageButton settingsBtn = findViewById(R.id.settingsbtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), SettingsActivity.class);
                myint.putExtra("user_id", user_id);
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

        Button notationsbtn = findViewById(R.id.notationsbtn);
        notationsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), NotationsActivity.class);
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

        Call<UserNameSubInfo> call = retrofitInterface.executeUserNameSub(map);
        call.enqueue(new Callback<UserNameSubInfo>() {
            @Override
            public void onResponse(Call<UserNameSubInfo> call, Response<UserNameSubInfo> response) {

                if (response.code() == 200) {

                    UserNameSubInfo result = response.body();
                    String user_name = result.getName();
                    user_sub = (result.getSub() == 1? "Преміум" : "Стандартна");

                    nameTextView = findViewById(R.id.name);
                    sub_typeTextView = findViewById(R.id.subscrtype);


                    nameTextView.setText(user_name);
                    sub_typeTextView.setText(user_sub);

                } else if (response.code() == 404) {
                    Toast.makeText(ProfileActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserNameSubInfo> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Name" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });




        map = new HashMap<>();

        map.put("user_id", Integer.toString(user_id));

        Call<ArrayList<UserNotationsInfo>> call1 = retrofitInterface.executeUserNotations(map);
        call1.enqueue(new Callback<ArrayList<UserNotationsInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserNotationsInfo>> call, Response<ArrayList<UserNotationsInfo>> response) {

                if (response.code() == 200) {

                    ArrayList<UserNotationsInfo> result = response.body();


                    String notation_names = "";
                    for (int i = 0; i < result.size(); i++)
                        notation_names += "- " + result.get(i).getNote_names() + "\n";


                    all_notaionsTextView = findViewById(R.id.allnotaions);

                    //Toast.makeText(ProfileActivity.this, user_name, Toast.LENGTH_LONG).show();

                    all_notaionsTextView.setText(notation_names);
                    //all_notaionsTextView.setText(all_notation_names);

                } else if (response.code() == 404) {
                    Toast.makeText(ProfileActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserNotationsInfo>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Notes" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


        map = new HashMap<>();

        map.put("user_id", Integer.toString(user_id));

        Call<ArrayList<UserAllergensInfo>> call2 = retrofitInterface.executeUserAllergens(map);
        call2.enqueue(new Callback<ArrayList<UserAllergensInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserAllergensInfo>> call, Response<ArrayList<UserAllergensInfo>> response) {

                if (response.code() == 200) {

                    TextView allergensTextView = findViewById(R.id.allallergens);
                    ArrayList<UserAllergensInfo> result = response.body();

                    String allergens = "";
                    for (int i = 0; i < result.size(); i++)
                        allergens += "- " + result.get(i).getAllergens() + "\n";


                    allergensTextView.setText(allergens);

                } else if (response.code() == 404) {
                    Toast.makeText(ProfileActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserAllergensInfo>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Allergens" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        Button recommendBtn = findViewById(R.id.recommendationsbtn);
        recommendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), RecommendationsActivity.class);
                myint.putExtra("user_id", user_id);
                startActivity(myint);
            }
        });

        Button allergensbtn = findViewById(R.id.allergensbtn);
        allergensbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAllergenPopup();
            }
        });
    }

    private void CreateAllergenPopup() {
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.popup_add_allergen,null);
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
        Button AddButton;
        AddButton = popUpView.findViewById(R.id.addbtn);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HERE WE ADD ALLERGEN
                HashMap<String, String> map = new HashMap<>();

                map.put("user_id", Integer.toString(user_id));
                map.put("allergen_name", spinner.getSelectedItem().toString());

                Call<AllergensNamesInfo> call = retrofitInterface.executeAddAllergen(map);
                call.enqueue(new Callback<AllergensNamesInfo>() {
                    @Override
                    public void onResponse(Call<AllergensNamesInfo> call, Response<AllergensNamesInfo> response) {

                        if (response.code() == 200) {

                        } else if (response.code() == 404) {
                            Toast.makeText(ProfileActivity.this, "Something went wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllergensNamesInfo> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, "Popup allergens add" + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

                Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                myint.putExtra("user_id", user_id);
                startActivity(myint);
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

        //fill allergens from db
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", Integer.toString(user_id));
        Call<ArrayList<AllergensNamesInfo>> call = retrofitInterface.executeAllergenNamesInfo(map);

        call.enqueue(new Callback<ArrayList<AllergensNamesInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<AllergensNamesInfo>> call, Response<ArrayList<AllergensNamesInfo>> response) {

                if (response.code() == 200) {

                    spinner = popUpView.findViewById(R.id.spinner);
                    ArrayList<String> spinnerArray = new ArrayList<String>();
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
                    ArrayList<AllergensNamesInfo> result = response.body();

                    for (int i = 0; i < result.size(); i++){
                        spinnerArray.add(result.get(i).getAllergens().toString());
                    }
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(spinnerArrayAdapter);

                } else if (response.code() == 404) {
                    Toast.makeText(ProfileActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AllergensNamesInfo>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "popup allergens display" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
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
                ImageView payButton = newPopupView.findViewById(R.id.paybtn);
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

    }



}