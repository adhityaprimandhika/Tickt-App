package com.adhityaprimandhika.tix.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.adhityaprimandhika.tix.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnHome : ImageView = findViewById(R.id.btn_home)
        val btnTicket : ImageView = findViewById(R.id.btn_ticket)
        val btnProfile : ImageView = findViewById(R.id.btn_profile)

        val fragmentHome = DashboardFragment()
        val fragmentTicket = TicketFragment()
        val fragmentProfile = ProfileFragment()

        setFragment(fragmentHome)

        btnHome.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(btnHome, R.drawable.ic_home_active)
            changeIcon(btnTicket, R.drawable.ic_tickets)
            changeIcon(btnProfile, R.drawable.ic_profile)
        }

        btnTicket.setOnClickListener {
            setFragment(fragmentTicket)

            changeIcon(btnHome, R.drawable.ic_home)
            changeIcon(btnTicket, R.drawable.ic_tickets_active)
            changeIcon(btnProfile, R.drawable.ic_profile)
        }

        btnProfile.setOnClickListener {
            setFragment(fragmentProfile)

            changeIcon(btnHome, R.drawable.ic_home)
            changeIcon(btnTicket, R.drawable.ic_tickets)
            changeIcon(btnProfile, R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }
}