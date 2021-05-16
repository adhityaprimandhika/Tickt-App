package com.adhityaprimandhika.tickt.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.sign.login.LoginActivity
import com.adhityaprimandhika.tickt.utils.Preferences

class OnboardingThreeActivity : AppCompatActivity() {

    private lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        preferences = Preferences(this)
        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val getStartedIntent = Intent(this@OnboardingThreeActivity, LoginActivity::class.java)
            startActivity(getStartedIntent)
        }
        val btnGetStarted : Button = findViewById(R.id.btn_get_started)
        btnGetStarted.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity()

            val getStartedIntent = Intent(this@OnboardingThreeActivity, LoginActivity::class.java)
            startActivity(getStartedIntent)
        }
    }
}