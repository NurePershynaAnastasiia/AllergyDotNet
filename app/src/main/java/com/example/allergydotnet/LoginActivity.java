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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        Button btn = findViewById(R.id.signupbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    startActivity(myint);
                }
            }
        });

        TextView createNew = findViewById(R.id.createnew);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(myint);
            }
        });
    }
}