package com.example.allergydotnet

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton


public class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        val username = findViewById<View>(R.id.username) as EditText

        val regbtn = findViewById<View>(R.id.signupbtn) as MaterialButton

        regbtn.setOnClickListener {
            val username1 = username.text.toString()
            Toast.makeText(this@RegisterActivity, "Username is$username1", Toast.LENGTH_SHORT).show()
        }

    }
}
