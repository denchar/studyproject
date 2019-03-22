package com.example.studyapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.studyapplication.MovieModels.MovieReviewModel
import com.example.studyapplication.MovieModels.MovieVideoModel
import com.example.studyapplication.presenters.MovieDetailPresenter
import com.example.studyapplication.recyclerViews.DetailReviewRecyclerViewAdapter
import com.example.studyapplication.recyclerViews.DetailTrailersRecyclerViewAdapter
import com.example.studyapplication.viewInterfaces.MovieDetailInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailInterface.ViewInterface,
    DetailTrailersRecyclerViewAdapter.ViewHolderClickListener {


    lateinit var presenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        presenter = MovieDetailPresenter(this)
        if (isOnline(applicationContext)) {
            presenter.getRequest(
                intent.getIntExtra(ID, Values.DEFAULT_ID),
                intent.getBooleanExtra(VIDEO, false)
            )
        } else {
            Toast.makeText(applicationContext, R.string.no_сonnection, Toast.LENGTH_SHORT).show()
        }
        setMovieInfo()
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

    private fun setMovieInfo() {
        Picasso.get()
            .load(
                Values.BASE_IMAGE_URL
                        + Values.RECOMENDED_IMAGE_SIZE
                        + intent.getStringExtra(POSTER_PATH)
            ).into(poster_image_view)

        val stringRelease = this.resources.getString(R.string.release) + intent.getStringExtra(RELEASE_DATE).toString()
        val stringVoteAverage =
            this.resources.getString(R.string.vote_average) +
                    intent.getStringExtra(VOTE_AVERAGE).toString() +
                    this.resources.getString(
                        R.string.from_ten
                    )
        movie_title_text_view.text = intent.getStringExtra(TITLE)
        release_date_text_view.text = stringRelease
        movie_vote_average_text_view.text = stringVoteAverage
        overview_text_view.text = intent.getStringExtra(OVERVIEW)

    }

    override fun startProgressVideo() {
        trailers_progress_bar.visibility = View.VISIBLE
        trailers_recycler_view.visibility = View.GONE
    }

    override fun stopProgressVideo() {
        trailers_progress_bar.visibility = View.GONE
        trailers_recycler_view.visibility = View.VISIBLE
    }

    override fun showResultVideo(video: ArrayList<MovieVideoModel.Result>) {
        trailers_recycler_view.layoutManager = LinearLayoutManager(this)
        trailers_recycler_view.adapter = DetailTrailersRecyclerViewAdapter(video, this)
    }

    override fun startProgressReview() {
        reviews_progress_bar.visibility = View.VISIBLE
        reviews_recycler_view.visibility = View.GONE
    }

    override fun stopProgressReview() {
        reviews_progress_bar.visibility = View.GONE
        reviews_recycler_view.visibility = View.VISIBLE
    }

    override fun showResultReview(review: ArrayList<MovieReviewModel.Result>) {
        reviews_recycler_view.layoutManager = LinearLayoutManager(this)
        reviews_recycler_view.adapter = DetailReviewRecyclerViewAdapter(review)
    }

    override fun onViewHolderClick(result: MovieVideoModel.Result) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(this.resources.getString(R.string.youtube_url) + result.key)
            )
        )
    }

    override fun showErrResultVideos() {
        err_video_text_view.visibility = View.VISIBLE
    }

    override fun showErrResultReviews() {
        err_review_text_view.visibility = View.VISIBLE
    }

    companion object {
        private const val ID = "id"
        private const val VOTE_COUNT = "vote_count"
        private const val VIDEO = "video"
        private const val VOTE_AVERAGE = "vote_average"
        private const val TITLE = "title"
        private const val POPULARITY = "popularity"
        private const val POSTER_PATH = "poster_path"
        private const val BACKDROP_PATH = "backdrop_path"
        private const val OVERVIEW = "overview"
        private const val RELEASE_DATE = "release_date"

        fun startMovieDetailActivity(
            context: Context,
            id: Int,
            vote_count: Int,
            video: Boolean,
            vote_average: Float,
            title: String,
            popularity: Float,
            poster_path: String,
            backdrop_path: String,
            overview: String,
            release_date: String
        ) {
            context.startActivity(
                Intent(context, MovieDetailActivity::class.java)
                    .putExtra(ID, id)
                    .putExtra(VOTE_COUNT, vote_count.toString())
                    .putExtra(VIDEO, video)
                    .putExtra(VOTE_AVERAGE, vote_average.toString())
                    .putExtra(TITLE, title)
                    .putExtra(POPULARITY, popularity.toString())
                    .putExtra(POSTER_PATH, poster_path)
                    .putExtra(BACKDROP_PATH, backdrop_path)
                    .putExtra(OVERVIEW, overview)
                    .putExtra(RELEASE_DATE, release_date)
            )
        }
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}
