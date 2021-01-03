package com.adhityaprimandhika.tix.sign

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.adhityaprimandhika.tix.R
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            // Write a message to the database
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }
        val btnCreateAccount: Button = findViewById(R.id.btn_create_account)
        btnCreateAccount.setOnClickListener{

        }
    }
}