package com.adhityaprimandhika.tickt.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adhityaprimandhika.tickt.DetailActivity
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.model.Checkout
import com.adhityaprimandhika.tickt.model.Film
import kotlinx.android.synthetic.main.activity_choose_seat.*

class ChooseSeatActivity : AppCompatActivity() {

    var statusA1 : Boolean = false
    var statusA2 : Boolean = false
    var statusA3 : Boolean = false
    var statusA4 : Boolean = false
    var statusB1 : Boolean = false
    var statusB2 : Boolean = false
    var statusB3 : Boolean = false
    var statusB4 : Boolean = false
    var statusC1 : Boolean = false
    var statusC2 : Boolean = false
    var statusC3 : Boolean = false
    var statusC4 : Boolean = false
    var statusD1 : Boolean = false
    var statusD2 : Boolean = false
    var statusD3 : Boolean = false
    var statusD4 : Boolean = false
    var statusE1 : Boolean = false
    var statusE2 : Boolean = false
    var statusE3 : Boolean = false
    var statusE4 : Boolean = false
    var total : Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seat)

        val data = intent.getParcelableExtra<Film>("data")

        if (data != null) {
            movie_title.text = data.title
        }

        a1.setOnClickListener {
            if (statusA1) {
                a1.setImageResource(R.drawable.shape_rectangle_grey)
                statusA1 = false
                total -= 1
                buyTicket(total)
            }else {
                a1.setImageResource(R.drawable.shape_rectangle_green)
                statusA1 = true
                total += 1
                buyTicket(total)

                val data = Checkout("A1", "25000")
                dataList.add(data)
            }
        }
        a2.setOnClickListener {
            if (statusA2) {
                a2.setImageResource(R.drawable.shape_rectangle_grey)
                statusA2 = false
                total -= 1
                buyTicket(total)
            }else {
                a2.setImageResource(R.drawable.shape_rectangle_green)
                statusA2 = true
                total += 1
                buyTicket(total)

                val data = Checkout("A2", "25000")
                dataList.add(data)
            }
        }
        a3.setOnClickListener {
            if (statusA3) {
                a3.setImageResource(R.drawable.shape_rectangle_grey)
                statusA3 = false
                total -= 1
                buyTicket(total)
            }else {
                a3.setImageResource(R.drawable.shape_rectangle_green)
                statusA3 = true
                total += 1
                buyTicket(total)

                val data = Checkout("A3", "25000")
                dataList.add(data)
            }
        }
        a4.setOnClickListener {
            if (statusA4) {
                a4.setImageResource(R.drawable.shape_rectangle_grey)
                statusA4 = false
                total -= 1
                buyTicket(total)
            }else {
                a4.setImageResource(R.drawable.shape_rectangle_green)
                statusA4 = true
                total += 1
                buyTicket(total)

                val data = Checkout("A4", "25000")
                dataList.add(data)
            }
        }
        b1.setOnClickListener {
            if (statusB1) {
                b1.setImageResource(R.drawable.shape_rectangle_grey)
                statusB1 = false
                total -= 1
                buyTicket(total)
            }else {
                b1.setImageResource(R.drawable.shape_rectangle_green)
                statusB1 = true
                total += 1
                buyTicket(total)

                val data = Checkout("B1", "25000")
                dataList.add(data)
            }
        }
        b2.setOnClickListener {
            if (statusB2) {
                b2.setImageResource(R.drawable.shape_rectangle_grey)
                statusB2 = false
                total -= 1
                buyTicket(total)
            }else {
                b2.setImageResource(R.drawable.shape_rectangle_green)
                statusB2 = true
                total += 1
                buyTicket(total)

                val data = Checkout("B2", "25000")
                dataList.add(data)
            }
        }
        b3.setOnClickListener {
            if (statusB3) {
                b3.setImageResource(R.drawable.shape_rectangle_grey)
                statusB3 = false
                total -= 1
                buyTicket(total)
            }else {
                b3.setImageResource(R.drawable.shape_rectangle_green)
                statusB3 = true
                total += 1
                buyTicket(total)

                val data = Checkout("B3", "25000")
                dataList.add(data)
            }
        }
        b4.setOnClickListener {
            if (statusB4) {
                b4.setImageResource(R.drawable.shape_rectangle_grey)
                statusB4 = false
                total -= 1
                buyTicket(total)
            }else {
                b4.setImageResource(R.drawable.shape_rectangle_green)
                statusB4 = true
                total += 1
                buyTicket(total)

                val data = Checkout("B4", "25000")
                dataList.add(data)
            }
        }
        c1.setOnClickListener {
            if (statusC1) {
                c1.setImageResource(R.drawable.shape_rectangle_grey)
                statusC1 = false
                total -= 1
                buyTicket(total)
            }else {
                c1.setImageResource(R.drawable.shape_rectangle_green)
                statusC1 = true
                total += 1
                buyTicket(total)

                val data = Checkout("C1", "25000")
                dataList.add(data)
            }
        }
        c2.setOnClickListener {
            if (statusC2) {
                c2.setImageResource(R.drawable.shape_rectangle_grey)
                statusC2 = false
                total -= 1
                buyTicket(total)
            }else {
                c2.setImageResource(R.drawable.shape_rectangle_green)
                statusC2 = true
                total += 1
                buyTicket(total)

                val data = Checkout("C2", "25000")
                dataList.add(data)
            }
        }
        c3.setOnClickListener {
            if (statusC3) {
                c3.setImageResource(R.drawable.shape_rectangle_grey)
                statusC3 = false
                total -= 1
                buyTicket(total)
            }else {
                c3.setImageResource(R.drawable.shape_rectangle_green)
                statusC3 = true
                total += 1
                buyTicket(total)

                val data = Checkout("C3", "25000")
                dataList.add(data)
            }
        }
        c4.setOnClickListener {
            if (statusC4) {
                c4.setImageResource(R.drawable.shape_rectangle_grey)
                statusC4 = false
                total -= 1
                buyTicket(total)
            }else {
                c4.setImageResource(R.drawable.shape_rectangle_green)
                statusC4 = true
                total += 1
                buyTicket(total)

                val data = Checkout("C4", "25000")
                dataList.add(data)
            }
        }
        d1.setOnClickListener {
            if (statusD1) {
                d1.setImageResource(R.drawable.shape_rectangle_grey)
                statusD1 = false
                total -= 1
                buyTicket(total)
            }else {
                d1.setImageResource(R.drawable.shape_rectangle_green)
                statusD1 = true
                total += 1
                buyTicket(total)

                val data = Checkout("D1", "25000")
                dataList.add(data)
            }
        }
        d2.setOnClickListener {
            if (statusD2) {
                d2.setImageResource(R.drawable.shape_rectangle_grey)
                statusD2 = false
                total -= 1
                buyTicket(total)
            }else {
                d2.setImageResource(R.drawable.shape_rectangle_green)
                statusD2 = true
                total += 1
                buyTicket(total)

                val data = Checkout("D2", "25000")
                dataList.add(data)
            }
        }
        d3.setOnClickListener {
            if (statusD3) {
                d3.setImageResource(R.drawable.shape_rectangle_grey)
                statusD3 = false
                total -= 1
                buyTicket(total)
            }else {
                d3.setImageResource(R.drawable.shape_rectangle_green)
                statusD3 = true
                total += 1
                buyTicket(total)

                val data = Checkout("D3", "25000")
                dataList.add(data)
            }
        }
        d4.setOnClickListener {
            if (statusD4) {
                d4.setImageResource(R.drawable.shape_rectangle_grey)
                statusD4 = false
                total -= 1
                buyTicket(total)
            }else {
                d4.setImageResource(R.drawable.shape_rectangle_green)
                statusD4 = true
                total += 1
                buyTicket(total)

                val data = Checkout("D4", "25000")
                dataList.add(data)
            }
        }
        e1.setOnClickListener {
            if (statusE1) {
                e1.setImageResource(R.drawable.shape_rectangle_grey)
                statusE1 = false
                total -= 1
                buyTicket(total)
            }else {
                e1.setImageResource(R.drawable.shape_rectangle_green)
                statusE1 = true
                total += 1
                buyTicket(total)

                val data = Checkout("E1", "25000")
                dataList.add(data)
            }
        }
        e2.setOnClickListener {
            if (statusE2) {
                e2.setImageResource(R.drawable.shape_rectangle_grey)
                statusE2 = false
                total -= 1
                buyTicket(total)
            }else {
                e2.setImageResource(R.drawable.shape_rectangle_green)
                statusE2 = true
                total += 1
                buyTicket(total)

                val data = Checkout("E2", "25000")
                dataList.add(data)
            }
        }
        e3.setOnClickListener {
            if (statusE3) {
                e3.setImageResource(R.drawable.shape_rectangle_grey)
                statusE3 = false
                total -= 1
                buyTicket(total)
            }else {
                e3.setImageResource(R.drawable.shape_rectangle_green)
                statusE3 = true
                total += 1
                buyTicket(total)

                val data = Checkout("E3", "25000")
                dataList.add(data)
            }
        }
        e4.setOnClickListener {
            if (statusE4) {
                e4.setImageResource(R.drawable.shape_rectangle_grey)
                statusE4 = false
                total -= 1
                buyTicket(total)
            }else {
                e4.setImageResource(R.drawable.shape_rectangle_green)
                statusE4 = true
                total += 1
                buyTicket(total)

                val data = Checkout("E4", "25000")
                dataList.add(data)
            }
        }

        btn_back.setOnClickListener {
            var backIntent = Intent(this@ChooseSeatActivity, DetailActivity::class.java)
            startActivity(backIntent)
        }

        btn_buy_ticket.setOnClickListener {
            var buyIntent = Intent(this@ChooseSeatActivity, CheckoutActivity::class.java).putExtra("data", dataList)
            startActivity(buyIntent)
        }
    }

    private fun buyTicket(total: Int) {
        if (total == 0) {
            btn_buy_ticket.setText("BUY TICKET")
            btn_buy_ticket.visibility = View.INVISIBLE
        }else {
            btn_buy_ticket.setText("BUY " + total + " TICKET(S)")
            btn_buy_ticket.visibility = View.VISIBLE
        }
    }
}