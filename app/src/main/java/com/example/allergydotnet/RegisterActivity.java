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

import com.example.allergydotnet.util.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://localhost:3000/userLogIn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //// new code for work with sever
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        handleRegisterInfo();
        ///// new code for work with sever
        /*
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText repasswordEditText = findViewById(R.id.repasswordEditText);
        Button btn = findViewById(R.id.signupbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String textName = usernameEditText.getText().toString();
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
                    Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(myint);
                }
            }
        });*/

        TextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(myint);
            }
        });
    }

    private void handleRegisterInfo() {
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText repasswordEditText = findViewById(R.id.repasswordEditText);

        Button signupBtn = findViewById(R.id.signupbtn);
        final EditText nameEdit = findViewById(R.id.usernameEditText);
        final EditText emailEdit = findViewById(R.id.emailEditText);
        final EditText passwordEdit = findViewById(R.id.passwordEditText);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            String textName = usernameEditText.getText().toString();
            String textEmail = emailEditText.getText().toString();
            String textPassword = passwordEditText.getText().toString();
            String textRepassword = repasswordEditText.getText().toString();
            @Override
            public void onClick(View view) {

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
                    Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(myint);
                }

                HashMap<String, String> map = new HashMap<>();

                map.put("name", nameEdit.getText().toString());
                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Toast.makeText(RegisterActivity.this,
                                    "Signed up successfully", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400) {
                            Toast.makeText(RegisterActivity.this,
                                    "Already registered", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}