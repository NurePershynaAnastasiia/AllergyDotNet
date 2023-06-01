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
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://localhost:3000/userLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    //// new code for work with sever
        /*retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        //handleLoginInfo();
    ///// new code for work with sever
        //EditText emailEditText = findViewById(R.id.emailEditText);
        //EditText passwordEditText = findViewById(R.id.passwordEditText);
        //Button btn = findViewById(R.id.signupbtn);
        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textEmail = emailEditText.getText().toString();
                String textPassword = passwordEditText.getText().toString();


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
                    startActivity(myint);
                    //
                }
            }
        });*/

        TextView createNew = findViewById(R.id.createnew);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(myint);
                //handleLoginDialog
            }
        });
    }

    //// methods for work with server ////
    private void handleLoginInfo() {

        View view = getLayoutInflater().inflate(R.layout.activity_login, null);

        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setView(view).show();

        Button signipBtn = view.findViewById(R.id.signupbtn);
        final EditText emailEditText = view.findViewById(R.id.emailEditText);
        final EditText passwordEditText = view.findViewById(R.id.passwordEditText);
        String textEmail = emailEditText.getText().toString();
        String textPassword = passwordEditText.getText().toString();

        signipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    HashMap<String, String> map = new HashMap<>();

                    map.put("user_email", textEmail);
                    map.put("user_password", textPassword);

                    Call<LoginInfo> call = retrofitInterface.executeLogin(map);
                    call.enqueue(new Callback<LoginInfo>() {
                        @Override
                        public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {

                            if (response.code() == 200) {

                                LoginInfo result = response.body();
                                //AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                //builder1.setTitle(result.getName());
                                //builder1.setMessage(result.getEmail());
                                //builder1.show();

                                Toast.makeText(LoginActivity.this, "Cool",
                                        Toast.LENGTH_LONG).show();
                                Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
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



                    //
                }



            }
        });

    }
}