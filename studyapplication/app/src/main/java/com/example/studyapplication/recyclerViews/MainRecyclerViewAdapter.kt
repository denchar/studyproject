package com.example.studyapplication.recyclerViews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.studyapplication.Values
import com.example.studyapplication.MovieModels.MovieModel
import com.example.studyapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_layout.view.*

class MainRecyclerViewAdapter(var list: ArrayList<MovieModel>, var listener: ViewHolderClickListener) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewModel>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewModel {
        return ViewModel(LayoutInflater.from(p0.context).inflate(R.layout.item_movie_layout, p0, false), listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewModel, p1: Int) {

        p0.title.text = list[p1].title
        val url = Values.BASE_IMAGE_URL + Values.RECOMENDED_IMAGE_SIZE + list[p1].poster_path
        Picasso.get().load(url).into(p0.poster)
        p0.bind(list[p1])
    }

    interface ViewHolderClickListener {
        fun onViewHolderClick(item: MovieModel)
    }

    class ViewModel(view: View, listener: ViewHolderClickListener) : RecyclerView.ViewHolder(view) {

        var poster: ImageView = view.poster_image_view
        var title: TextView = view.movie_title_text_view
        lateinit var movieModel: MovieModel

        init {
            itemView.setOnClickListener {
                listener.onViewHolderClick(movieModel)
            }
        }

        fun bind(movieModel: MovieModel) {
            this.movieModel = movieModel
        }
    }
}