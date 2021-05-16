package com.adhityaprimandhika.tickt.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adhityaprimandhika.tickt.R
import com.adhityaprimandhika.tickt.model.Film
import com.bumptech.glide.Glide

class NowPlayingAdapter(private var data: List<Film>,
                        private val listener : (Film) -> Unit)
    : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_row_now_playing, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NowPlayingAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieTitle : TextView = view.findViewById(R.id.movie_title)
        private val movieGenre : TextView = view.findViewById(R.id.movie_genre)
        private val movieRating : TextView = view.findViewById(R.id.movie_rating)
        private val moviePoster : ImageView = view.findViewById(R.id.movie_poster)

        fun bindItem(data: Film, listener: (Film) -> Unit, context : Context) {
            movieTitle.setText(data.title)
            movieGenre.setText(data.genre)
            movieRating.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(moviePoster)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
