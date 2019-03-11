package com.example.studyapplication.recyclerViews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyapplication.MovieModels.MovieReviewModel
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.item_review_layout.view.*

class DetailReviewRecyclerViewAdapter(var list: ArrayList<MovieReviewModel.Result>) :
    RecyclerView.Adapter<DetailReviewRecyclerViewAdapter.ViewModel>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewModel {
        return ViewModel(LayoutInflater.from(p0.context).inflate(R.layout.item_review_layout, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewModel, p1: Int) {
        p0.author.text = list[p1].author
        p0.content.text = list[p1].content
    }

    class ViewModel(view: View) : RecyclerView.ViewHolder(view) {
        var author = view.author_text_view!!
        var content = view.content_text_view!!
    }
}