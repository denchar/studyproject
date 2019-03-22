package com.example.studyapplication.viewInterfaces

import com.example.studyapplication.MovieModels.MovieReviewModel
import com.example.studyapplication.MovieModels.MovieVideoModel

interface MovieDetailInterface {
    interface ViewInterface {
        fun startProgressVideo()
        fun stopProgressVideo()
        fun startProgressReview()
        fun stopProgressReview()
        fun showResultReview(review: ArrayList<MovieReviewModel.Result>)
        fun showResultVideo(video: ArrayList<MovieVideoModel.Result>)
        fun showErrResultVideos()
        fun showErrResultReviews()
    }

    interface Presenter {
        fun getRequest(id: Int, video: Boolean)
        fun returnResultVideos(video: ArrayList<MovieVideoModel.Result>)
        fun returnResultReviews(review: ArrayList<MovieReviewModel.Result>)
        fun returnErrResultVideos()
        fun returnErrResultReviews()
        fun clearDisposable()
    }

    interface Model {
        fun getRequest(id: Int, video: Boolean)
        fun clearDisposable()
    }
}