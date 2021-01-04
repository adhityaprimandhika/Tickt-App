package com.adhityaprimandhika.tix.sign.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adhityaprimandhika.tix.HomeActivity
import com.adhityaprimandhika.tix.R
import com.adhityaprimandhika.tix.sign.signup.SignUpActivity
import com.adhityaprimandhika.tix.utils.Preferences
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String

    private lateinit var mDatabase: DatabaseReference
    private lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername : EditText = findViewById(R.id.et_username_login)
        val etPassword : EditText = findViewById(R.id.et_password_login)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")) {
            finishAffinity()

            val homeIntent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(homeIntent)
        }
        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            iUsername = etUsername.text.toString()
            iPassword = etPassword.text.toString()

            if (iUsername.isEmpty()) {
                etUsername.error = "Please fill in your username"
                etUsername.requestFocus()
            }else if (iPassword.isEmpty()) {
                etPassword.error = "Please fill in your password"
                etPassword.requestFocus()
            }else {
                pushLogin(iUsername,iPassword)
            }
        }

        val btnCreateAccount: Button = findViewById(R.id.btn_create_account)
        btnCreateAccount.setOnClickListener {
            val signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)

                if (user == null) {
                    Toast.makeText(this@LoginActivity, "Username not found", Toast.LENGTH_LONG).show()
                }else {
                    if (user.password.equals(iPassword)) {
                        preference.setValues("name", user.name.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("balance", user.balance.toString())
                        preference.setValues("status", "1")

                        val loginIntent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(loginIntent)
                    }else {
                        Toast.makeText(this@LoginActivity, "Password incorrect", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@LoginActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}