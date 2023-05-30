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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText repasswordEditText = findViewById(R.id.repasswordEditText);
        Button btn = findViewById(R.id.signupbtn);
        btn.setOnClickListener(new View.OnClickListener() {
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
                    Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(myint);
                }
            }
        });

        TextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(myint);
            }
        });
    }
}