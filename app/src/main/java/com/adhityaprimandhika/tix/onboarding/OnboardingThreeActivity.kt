package com.adhityaprimandhika.tix.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.adhityaprimandhika.tix.R
import com.adhityaprimandhika.tix.sign.LoginActivity

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        val btnGetStarted : Button = findViewById(R.id.btn_get_started)
        btnGetStarted.setOnClickListener {
            finishAffinity()

            val getStartedIntent = Intent(this@OnboardingThreeActivity, LoginActivity::class.java)
            startActivity(getStartedIntent)
        }
    }
}