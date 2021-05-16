package com.adhityaprimandhika.tickt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhityaprimandhika.tickt.checkout.ChooseSeatActivity
import com.adhityaprimandhika.tickt.home.HomeActivity
import com.adhityaprimandhika.tickt.home.dashboard.PlaysAdapter
import com.adhityaprimandhika.tickt.model.Film
import com.adhityaprimandhika.tickt.model.Plays
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnBack : ImageView = findViewById(R.id.btn_back)
        val btnChoose : Button = findViewById(R.id.btn_choose_seat)

        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data!!.title.toString())
            .child("play")

        movie_title.text = data!!.title
        movie_genre.text = data!!.genre
        movie_synopsis.text = data!!.desc
        movie_rating.text = data!!.rating

        Glide.with(this)
            .load(data.poster)
            .into(movie_poster)

        rv_actors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btnBack.setOnClickListener{
            var backIntent = Intent(this@DetailActivity, HomeActivity::class.java)
            startActivity(backIntent)
        }

        btnChoose.setOnClickListener {
            var chooseIntent = Intent(this@DetailActivity, ChooseSeatActivity::class.java).putExtra("data", data)
            startActivity(chooseIntent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()

                for(snapshot in dataSnapshot.children) {
                    var film = snapshot.getValue(Plays::class.java)
                    dataList.add(film!!)
                }

                rv_actors.adapter = PlaysAdapter(dataList) {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, "" + error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}