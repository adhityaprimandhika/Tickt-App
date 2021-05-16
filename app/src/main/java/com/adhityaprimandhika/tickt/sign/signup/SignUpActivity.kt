package com.adhityaprimandhika.tickt.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.sign.login.User
import com.adhityaprimandhika.tickt.utils.Preferences
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var sUsername : String
    private lateinit var sName : String
    private lateinit var sEmail : String
    private lateinit var sPassword : String

    private lateinit var mFirebaseReference : DatabaseReference
    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var mDatabase : DatabaseReference
    private lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etUsername : EditText = findViewById(R.id.et_username_sign_up)
        val etName : EditText = findViewById(R.id.et_name_sign_up)
        val etEmail : EditText = findViewById(R.id.et_email_sign_up)
        val etPassword : EditText = findViewById(R.id.et_password_sign_up)
        val btnSignUp : Button = findViewById(R.id.btn_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseReference = mFirebaseInstance.getReference("User")
        preferences = Preferences(this)

        btnSignUp.setOnClickListener {
            sUsername = etUsername.text.toString()
            sName = etName.text.toString()
            sEmail = etEmail.text.toString()
            sPassword = etPassword.text.toString()

            if (sUsername.isEmpty()) {
                etUsername.error = "Please fill in your username"
                etUsername.requestFocus()
            }else if (sName.isEmpty()) {
                etName.error = "Please fill in your name"
                etName.requestFocus()
            }else if (sEmail.isEmpty()) {
                etEmail.error = "Please fill in your email"
                etEmail.requestFocus()
            }else if (sPassword.isEmpty()) {
                etPassword.error = "Please fill in your password"
            }else {
                saveUsername(sUsername, sName, sEmail, sPassword)
            }
        }
    }

    private fun saveUsername(sUsername: String, sName: String, sEmail: String, sPassword: String) {
        var user = User()
        user.username = sUsername
        user.name = sName
        user.email = sEmail
        user.password = sPassword
        user.balance = 0.toString()

        if (sUsername != null) {
            checkUsername(sUsername, user)
        }
    }

    private fun checkUsername(sUsername: String, data: User) {
        mFirebaseReference.child(sUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                var checkDone : Boolean = false
                if (user == null && checkDone == false) {
                    mFirebaseReference.child(sUsername).setValue(data)

                    preferences.setValues("name", data.name.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("balance", "")
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    var addPhotoIntent = Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java).putExtra("data", data)
                    startActivity(addPhotoIntent)
                    checkDone = true
                    Toast.makeText(this@SignUpActivity, "Sign Up Succeed", Toast.LENGTH_LONG).show()
                }else if (user != null && checkDone == true){
                    Toast.makeText(this@SignUpActivity, "Username have been taken", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}