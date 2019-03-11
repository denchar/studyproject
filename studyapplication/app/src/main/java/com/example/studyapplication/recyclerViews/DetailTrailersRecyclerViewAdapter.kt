package com.example.studyapplication.recyclerViews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyapplication.MovieModels.MovieVideoModel
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.item_trailer_layout.view.*

class DetailTrailersRecyclerViewAdapter(
    var list: ArrayList<MovieVideoModel.Result>,
    var listener: DetailTrailersRecyclerViewAdapter.ViewHolderClickListener
) :
    RecyclerView.Adapter<DetailTrailersRecyclerViewAdapter.ViewModel>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewModel {
        return ViewModel(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_trailer_layout,
                p0, false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewModel, p1: Int) {
        p0.trailerName.text = list[p1].name
        p0.bind(list[p1])
    }

    interface ViewHolderClickListener {
        fun onViewHolderClick(result: MovieVideoModel.Result)
    }

    class ViewModel(view: View, listener: DetailTrailersRecyclerViewAdapter.ViewHolderClickListener) :
        RecyclerView.ViewHolder(view) {

        val trailerName = view.trailer_name_text_view!!
        lateinit var result: MovieVideoModel.Result

        init {
            itemView.setOnClickListener {
                listener.onViewHolderClick(result)
            }
        }

        fun bind(result: MovieVideoModel.Result) {
            this.result = result
        }
    }
}