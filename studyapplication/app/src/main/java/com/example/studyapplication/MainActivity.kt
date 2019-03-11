package com.example.studyapplication

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.studyapplication.MovieModels.MovieModel
import com.example.studyapplication.presenters.MainActivityPresenter
import com.example.studyapplication.recyclerViews.MainRecyclerViewAdapter
import com.example.studyapplication.viewInterfaces.MainInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainInterface.ViewInterface, MainRecyclerViewAdapter.ViewHolderClickListener {

    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
        if (isOnline(applicationContext)) {
            presenter.getPopular()
        } else {
            Toast.makeText(applicationContext, R.string.no_сonnection, Toast.LENGTH_SHORT).show()
            no_connect_text_view.visibility = View.VISIBLE
        }
    }

    private fun isOnline(context: Context): Boolean {
        var network = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        if (netInfo != null && netInfo.isConnected) {
            network = true
        }
        return network
    }

    override fun startProgress() {
        for_progress_bar_frame_layout.visibility = View.VISIBLE
        movie_recycler_view.visibility = View.GONE
    }

    override fun stopProgress() {
        for_progress_bar_frame_layout.visibility = View.GONE
        movie_recycler_view.visibility = View.VISIBLE
    }

    override fun showResultPopular(list: ArrayList<MovieModel>) {
        movie_recycler_view.layoutManager = GridLayoutManager(applicationContext, 2)
        movie_recycler_view.adapter = MainRecyclerViewAdapter(list, this)
    }

    override fun onViewHolderClick(item: MovieModel) {
        MovieDetailActivity.startMovieDetailActivity(
            this,
            item.id,
            item.vote_count,
            item.video,
            item.vote_average,
            item.title,
            item.popularity,
            item.poster_path,
            item.backdrop_path,
            item.overview,
            item.release_date
        )
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}
