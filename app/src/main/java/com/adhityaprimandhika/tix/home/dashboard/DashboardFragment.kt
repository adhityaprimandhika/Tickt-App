package com.adhityaprimandhika.tix.home.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adhityaprimandhika.tix.R
import com.adhityaprimandhika.tix.model.Film
import com.adhityaprimandhika.tix.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var tvName : TextView
    private lateinit var tvBalance : TextView
    private lateinit var imgUser : ImageView
    private lateinit var rvNowPlaying : RecyclerView
    private lateinit var rvComingSoon : RecyclerView

    private lateinit var preferences : Preferences
    private lateinit var mDatabase : DatabaseReference

    private var dataList = ArrayList<Film>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvName = view.findViewById(R.id.name_home)
        tvBalance = view.findViewById(R.id.balance)
        imgUser = view.findViewById(R.id.img_user_home)
        rvNowPlaying = view.findViewById(R.id.rv_now_playing)
        rvComingSoon = view.findViewById(R.id.rv_coming_soon)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tvName.setText(preferences.getValues("name"))
        if (preferences.getValues("balance") != "") {
            currency(preferences.getValues("balance")!!.toDouble(), tvBalance)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(imgUser)

        rvNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvComingSoon.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun currency(value : Double, text : TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)

        text.setText(format.format(value))
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for(getdataSnapshot in dataSnapshot.children) {
                    var film = getdataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rvNowPlaying.adapter = NowPlayingAdapter(dataList) {

                }

                rvComingSoon.adapter = ComingSoonAdapter(dataList) {

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}