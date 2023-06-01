package com.example.allergydotnet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allergydotnet.util.LoginInfo;
import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.SignupInfo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText repasswordEditText;
    private Button singupBtn;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://172.20.10.2:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        repasswordEditText = findViewById(R.id.repasswordEditText);
        singupBtn = findViewById(R.id.signupbtn);
        singupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textName = usernameEditText.getText().toString();
                String textEmail = emailEditText.getText().toString();
                String textPassword = passwordEditText.getText().toString();
                String textRepassword = repasswordEditText.getText().toString();

                //Check input fields
                if (TextUtils.isEmpty(textName)){
                    Toast.makeText(RegisterActivity.this, "Будь ласка, введіть ім'я", Toast.LENGTH_SHORT).show();
                    usernameEditText.setError("Ви не ввели ім'я");
                    usernameEditText.requestFocus();
                } else if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(RegisterActivity.this, "Будь ласка, введіть пошту", Toast.LENGTH_SHORT).show();
                    emailEditText.setError("Ви не ввели пошту");
                    emailEditText.requestFocus();
                } else if (TextUtils.isEmpty(textPassword) || textPassword.length() < 8){
                    Toast.makeText(RegisterActivity.this, "Будь ласка, введіть пароль, який не менше 8 символів", Toast.LENGTH_SHORT).show();
                    passwordEditText.setError("Ви не ввели коректний пароль");
                    passwordEditText.requestFocus();
                } else if (TextUtils.isEmpty(textRepassword) || !TextUtils.equals(textPassword, textRepassword)){
                    Toast.makeText(RegisterActivity.this, "Будь ласка, підтвердіть пароль", Toast.LENGTH_SHORT).show();
                    repasswordEditText.setError("Введені паролі не співпадають");
                    repasswordEditText.requestFocus();
                }
                else {
                    HashMap<String, String> map = new HashMap<>();

                    map.put("user_name", textName);
                    map.put("user_email", textEmail);
                    map.put("user_password", textPassword);
                    Call<SignupInfo> call = retrofitInterface.executeSignup(map);

                    call.enqueue(new Callback<SignupInfo>() {
                        @Override
                        public void onResponse(Call<SignupInfo> call, Response<SignupInfo> response) {

                            if (response.code() == 200) {
                                SignupInfo result = response.body();
                                int user_id = result.getId();
                                Toast.makeText(RegisterActivity.this, "Signed up successfully",
                                        Toast.LENGTH_LONG).show();
                                Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                                myint.putExtra("user_id", user_id);
                                startActivity(myint);

                            } else if (response.code() == 404) {
                                Toast.makeText(RegisterActivity.this, "Wrong Credentials",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignupInfo> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

//        TextView login = findViewById(R.id.login);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myint = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(myint);
//            }
//        });
    }
}