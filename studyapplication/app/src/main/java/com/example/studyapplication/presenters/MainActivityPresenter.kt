package com.example.studyapplication.presenters


import com.example.studyapplication.MovieModels.MovieModel
import com.example.studyapplication.models.MainActivityModel
import com.example.studyapplication.viewInterfaces.MainInterface

class MainActivityPresenter() : MainInterface.Presenter {


    lateinit var view: MainInterface.ViewInterface
    lateinit var model: MainInterface.Model

    override fun getPopular() {
        view.startProgress()
        model.getRequest()
    }

    override fun returnResultPopular(list: ArrayList<MovieModel>) {
        view.showResultPopular(list)
        view.stopProgress()
    }

    override fun returnErrResult() {
        view.showErrResult()
    }

    constructor(view: MainInterface.ViewInterface) : this() {
        this.view = view
        this.model = MainActivityModel(this)
    }

    override fun clearDisposable() {
        model.clearDisposable()
    }
}