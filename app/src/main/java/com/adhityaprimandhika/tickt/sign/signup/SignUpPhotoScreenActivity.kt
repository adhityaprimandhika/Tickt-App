package com.adhityaprimandhika.tickt.sign.signup

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adhityaprimandhika.tickt.home.HomeActivity
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.R.drawable
import com.adhityaprimandhika.tickt.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import com.adhityaprimandhika.tickt.sign.login.User
import com.google.firebase.database.*

class SignUpPhotoScreenActivity : AppCompatActivity(){

    var statusAdd : Boolean = false
    private lateinit var filePath : Uri

    private lateinit var storage : FirebaseStorage
    private lateinit var storageReference : StorageReference

    lateinit var user : User
    private lateinit var mFirebaseReference : DatabaseReference
    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo_screen)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mFirebaseReference = mFirebaseInstance.getReference("User")

        user = intent.getParcelableExtra("data")!!

        val imgAccount : ImageView = findViewById(R.id.img_account)
        val btnSave : Button = findViewById(R.id.btn_save_next)
        val namePhoto : TextView = findViewById(R.id.name_photo)
        val addPhoto : ImageView = findViewById(R.id.add_photo)
        val btnSkip : Button = findViewById(R.id.btn_skip)

        namePhoto.text = intent.getStringExtra("name")

        addPhoto.setOnClickListener {
            btnSave.visibility = View.INVISIBLE
            if (statusAdd) {
                statusAdd = false
                btnSave.visibility = View.VISIBLE
                imgAccount.setImageResource(drawable.ic_account)
                addPhoto.setImageResource(drawable.add_photo)
            }else {
                ImagePicker.with(this@SignUpPhotoScreenActivity)
                    .cameraOnly()
                    .start()
                statusAdd = true
                btnSave.visibility = View.INVISIBLE
            }
        }

        btnSkip.setOnClickListener {
            finishAffinity()

            val homeIntent = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
            startActivity(homeIntent)
        }

        btnSave.setOnClickListener {
            if (filePath != null) {
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                var ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            saveToFirebase(it.toString())
                        }
                    }
                    .addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener {
                        taskSnaphot -> var progress = 100.0 * taskSnaphot.bytesTransferred / taskSnaphot.totalByteCount
                        progressDialog.setMessage("Upload " + progress.toInt() + "%")
                    }
            }
        }
    }

    private fun saveToFirebase(url: String) {
        mFirebaseReference.child(user.username!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot : DataSnapshot) {
                user.url = url
                mFirebaseReference.child(user.username!!).setValue(user)

                preferences.setValues("name", user.name.toString())
                preferences.setValues("user", user.username.toString())
                preferences.setValues("balance", "")
                preferences.setValues("url", "")
                preferences.setValues("email", user.email.toString())
                preferences.setValues("status", "1")
                preferences.setValues("url", url)

                finishAffinity()

                val homeIntent = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
                startActivity(homeIntent)
            }

            override fun onCancelled(error : DatabaseError) {
                Toast.makeText(this@SignUpPhotoScreenActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Click upload later", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imgAccount : ImageView = findViewById(R.id.img_account)
        val btnSave : Button = findViewById(R.id.btn_save_next)
        val addPhoto : ImageView = findViewById(R.id.add_photo)

        if (resultCode == Activity.RESULT_OK) {
            statusAdd = true
            //Image Uri will not be null for RESULT_OK
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(imgAccount)

            btnSave.visibility = View.VISIBLE
            addPhoto.setImageResource(drawable.ic_delete)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}