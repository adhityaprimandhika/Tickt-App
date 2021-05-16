package com.adhityaprimandhika.tickt.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.model.Checkout
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener : (Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_row_checkout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val seats : TextView = view.findViewById(R.id.tv_seat)
        private val total : TextView = view.findViewById(R.id.tv_price)

        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context : Context) {

            val localID = Locale("in", "ID")
            val format = NumberFormat.getCurrencyInstance(localID)

            total.setText(format.format(data.price!!.toDouble()))

            if (data.seat!!.startsWith("Total")) {
                seats.setText(data.seat)
                seats.setCompoundDrawables(null, null, null, null)
            } else {
                seats.setText("Seat " + data.seat)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
