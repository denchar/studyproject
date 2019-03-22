package com.example.studyapplication.presenters

import com.example.studyapplication.MovieModels.MovieReviewModel
import com.example.studyapplication.MovieModels.MovieVideoModel
import com.example.studyapplication.models.MovieDetailModel
import com.example.studyapplication.viewInterfaces.MovieDetailInterface

class MovieDetailPresenter() : MovieDetailInterface.Presenter {


    lateinit var view: MovieDetailInterface.ViewInterface
    lateinit var model: MovieDetailInterface.Model

    override fun getRequest(id: Int, video: Boolean) {
        view.startProgressVideo()
        view.startProgressReview()
        model.getRequest(id, video)
    }

    override fun returnResultVideos(video: ArrayList<MovieVideoModel.Result>) {
        view.showResultVideo(video)
        view.stopProgressVideo()
    }

    override fun returnResultReviews(review: ArrayList<MovieReviewModel.Result>) {
        view.showResultReview(review)
        view.stopProgressReview()
    }
    override fun returnErrResultVideos() {
        view.showErrResultVideos()
    }

    override fun returnErrResultReviews() {
       view.showErrResultReviews()
    }
    constructor(view: MovieDetailInterface.ViewInterface) : this() {
        this.view = view
        this.model = MovieDetailModel(this)
    }

    override fun clearDisposable() {
        model.clearDisposable()
    }
}