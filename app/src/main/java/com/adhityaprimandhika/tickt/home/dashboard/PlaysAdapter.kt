package com.adhityaprimandhika.tickt.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.model.Plays
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlaysAdapter(private var data: List<Plays>,
                   private val listener : (Plays) -> Unit)
    : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaysAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_row_plays, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val actorName : TextView = view.findViewById(R.id.actor_name)
        private val actorPhoto : ImageView = view.findViewById(R.id.actor_photo)

        fun bindItem(data: Plays, listener: (Plays) -> Unit, context : Context) {
            actorName.setText(data.name)

            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(actorPhoto)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}