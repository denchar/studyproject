package com.example.studyapplication.models

import com.example.studyapplication.Values
import com.example.studyapplication.MovieModels.MovieReviewModel
import com.example.studyapplication.MovieModels.MovieVideoModel
import com.example.studyapplication.presenters.MovieDetailPresenter
import com.example.studyapplication.retrofit.ApiRetrofitClient
import com.example.studyapplication.retrofit.ApiRetrofitInterface
import com.example.studyapplication.viewInterfaces.MovieDetailInterface
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MovieDetailModel(private val presenter: MovieDetailPresenter) : MovieDetailInterface.Model {

    var compositeDisposable = CompositeDisposable()

    override fun getRequest(id: Int, video: Boolean) {
        getReview(id)
        getVideos(id)
    }

    private fun getReview(id: Int) {

        val api: ApiRetrofitInterface =
            ApiRetrofitClient.getApiRetrofitClient()!!.create(ApiRetrofitInterface::class.java)
        compositeDisposable.add(
            api.getMovieReview(id, Values.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> printResult(result) }, {
                    println(Throwable::message)
                    sendErrResultReviews()
                })
        )
    }

    private fun getVideos(id: Int) {

        val api: ApiRetrofitInterface =
            ApiRetrofitClient.getApiRetrofitClient()!!.create(ApiRetrofitInterface::class.java)
        compositeDisposable.add(
            api.getMovieVideos(id, Values.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> printVideos(result) }, {
                    println(Throwable::message)
                    sendErrResultVideos()
                })
        )
    }


    private fun printVideos(result: MovieVideoModel) {
        presenter.returnResultVideos(result.results)
    }

    private fun printResult(result: MovieReviewModel) {
        presenter.returnResultReviews(result.results)
    }

    private fun sendErrResultVideos() {
        presenter.returnErrResultVideos()
    }

    private fun sendErrResultReviews() {
        presenter.returnErrResultReviews()
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}