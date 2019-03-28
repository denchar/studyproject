package com.example.studyapplication.viewInterfaces

import com.example.studyapplication.MovieModels.MovieModel

interface MainInterface {
    interface ViewInterface {
        fun startProgress()
        fun stopProgress()
        fun showResultPopular(list: ArrayList<MovieModel>)
        fun showErrResult()
    }

    interface Presenter {
        fun getPopular()
        fun returnResultPopular(list: ArrayList<MovieModel>)
        fun clearDisposable()
        fun returnErrResult()
    }

    interface Model {
        fun getRequest()
        fun clearDisposable()
        fun sendErrResult()
    }
}