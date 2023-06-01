package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.allergydotnet.util.LoginInfo;
import com.example.allergydotnet.util.NoteInfo;
import com.example.allergydotnet.util.RetrofitInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class NewNotationActivity extends AppCompatActivity {


    Intent intent = getIntent();
    int user_id = intent.getIntExtra("user_id", 0);
    Button create_btnNote = findViewById(R.id.addbtn);

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://172.20.10.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notation);

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

        RelativeLayout top_menu = findViewById(R.id.top_menu);
        ImageButton toProfile = findViewById(R.id.profilebtn);
        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(myint);
            }
        });

        create_btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText newNotationName = findViewById(R.id.newNotationName);
                EditText newNotationText = findViewById(R.id.newNotationText);

                String noteName = newNotationName.getText().toString();
                String noteText = newNotationText.getText().toString();
                String currentTime = Calendar.getInstance().getTime().toString();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);

                if (TextUtils.isEmpty(noteName)){
                    Toast.makeText(NewNotationActivity.this, "Будь ласка, введіть назву нотатки", Toast.LENGTH_SHORT).show();
                    newNotationName.setError("Ви не ввели назву нотатки");
                    newNotationName.requestFocus();
                } else if (TextUtils.isEmpty(noteText)){
                    Toast.makeText(NewNotationActivity.this, "Будь ласка, введіть текст нотатки", Toast.LENGTH_SHORT).show();
                    newNotationText.setError("Ви не ввели текст нотатки");
                    newNotationText.requestFocus();
                } else {
                    HashMap<String, String> map = new HashMap<>();

                    map.put("note_name", noteName);
                    map.put("note_date", currentTime);
                    map.put("note_text", noteText);
                    map.put("user_id", String.valueOf(user_id));

                    Call<NoteInfo> call = retrofitInterface.executeNote(map);
                    call.enqueue(new Callback<NoteInfo>() {
                        @Override
                        public void onResponse(Call<NoteInfo> call, Response<NoteInfo> response) {


                            if (response.code() == 200) {

                                NoteInfo result = response.body();
                                //AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                //builder1.setTitle(result.getName());
                                //builder1.setMessage(result.getEmail());
                                //builder1.show();

                                Intent myint = new Intent(getApplicationContext(), NotationsActivity.class);
                                startActivity(myint);

                            } else if (response.code() == 404) {
                                Toast.makeText(NewNotationActivity.this, "Something went wrong",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<NoteInfo> call, Throwable t) {
                            Toast.makeText(NewNotationActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }
}