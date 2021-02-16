package com.adhityaprimandhika.tix.sign.signup

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
import com.adhityaprimandhika.tix.home.HomeActivity
import com.adhityaprimandhika.tix.R
import com.adhityaprimandhika.tix.R.drawable
import com.adhityaprimandhika.tix.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

@Suppress("DEPRECATION")
class SignUpPhotoScreenActivity : AppCompatActivity(){

    var statusAdd : Boolean = false
    private lateinit var filePath : Uri

    private lateinit var storage : FirebaseStorage
    private lateinit var storageReference : StorageReference
    private lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo_screen)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

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
                            preferences.setValues("url", it.toString())
                        }

                        finishAffinity()

                        val homeIntent = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
                        startActivity(homeIntent)
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