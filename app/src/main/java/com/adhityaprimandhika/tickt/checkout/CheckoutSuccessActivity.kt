package com.adhityaprimandhika.tickt.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.home.HomeActivity
import kotlinx.android.synthetic.main.activity_checkout_success.*

class CheckoutSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_home.setOnClickListener {
            var homeIntent = Intent(this@CheckoutSuccessActivity, HomeActivity::class.java)
            startActivity(homeIntent)
        }
    }
}