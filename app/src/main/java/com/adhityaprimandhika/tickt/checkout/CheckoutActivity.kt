package com.adhityaprimandhika.tickt.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.model.Checkout
import com.adhityaprimandhika.tickt.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total : Int = 0
    private lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in dataList.indices) {
            total += dataList[a].price!!.toInt()
        }

        dataList.add(Checkout("Total needs to pay", total.toString()))

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter = CheckoutAdapter(dataList) {

        }

        btn_pay_now.setOnClickListener {
            var payIntent = Intent(this@CheckoutActivity, CheckoutSuccessActivity::class.java)
            startActivity(payIntent)
        }

        btn_back.setOnClickListener {
            var backIntent = Intent(this@CheckoutActivity, ChooseSeatActivity::class.java)
            startActivity(backIntent)
        }

        btn_cancel.setOnClickListener {
            var cancelIntent = Intent(this@CheckoutActivity, ChooseSeatActivity::class.java)
            startActivity(cancelIntent)
        }
    }
}