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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.HashMap;

import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private Button signinBtn;
    EditText emailEditText;
    EditText passwordEditText;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView createNew = findViewById(R.id.createnew);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(myint);
            }
        });

        /*retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

         */

        //View view = getLayoutInflater().inflate(R.layout.activity_login, null);
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setView(view).show();

        signinBtn = findViewById(R.id.signupbtn);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = emailEditText.getText().toString();
                String textPassword = passwordEditText.getText().toString();
                //Check input fields
                if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(LoginActivity.this, "Будь ласка, введіть пошту", Toast.LENGTH_SHORT).show();
                    emailEditText.setError("Ви не ввели пошту");
                    emailEditText.requestFocus();
                } else if (TextUtils.isEmpty(textPassword)){
                    Toast.makeText(LoginActivity.this, "Будь ласка, введіть пароль", Toast.LENGTH_SHORT).show();
                    passwordEditText.setError("Ви не ввели пароль");
                    passwordEditText.requestFocus();
                } else {
                    Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                    //myint.putExtra("user_id", user_id);
                    startActivity(myint);


                    /*
                    HashMap<String, String> map = new HashMap<>();

                    map.put("user_email", textEmail);
                    map.put("user_password", textPassword);

                    Call<LoginInfo> call = retrofitInterface.executeLogin(map);
                    call.enqueue(new Callback<LoginInfo>() {
                        @Override
                        public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {


                            if (response.code() == 200) {

                                LoginInfo result = response.body();
                                int user_id = result.getId();
                                //AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                //builder1.setTitle(result.getName());
                                //builder1.setMessage(result.getEmail());
                                //builder1.show();

                                Toast.makeText(LoginActivity.this, "Successful login",
                                        Toast.LENGTH_LONG).show();
                                Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                                //myint.putExtra("user_id", user_id);
                                startActivity(myint);

                            } else if (response.code() == 404) {
                                Toast.makeText(LoginActivity.this, "Wrong Credentials",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginInfo> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                     */

                }
            }
        });
    }
}