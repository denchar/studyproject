package com.example.studyapplication.models


import android.app.Application
import com.example.studyapplication.Values
import com.example.studyapplication.MovieModels.MovieResponceModel
import com.example.studyapplication.presenters.MainActivityPresenter
import com.example.studyapplication.retrofit.ApiRetrofitClient
import com.example.studyapplication.retrofit.ApiRetrofitInterface
import com.example.studyapplication.viewInterfaces.MainInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityModel(private val presenter: MainActivityPresenter) : MainInterface.Model {


    lateinit var api: ApiRetrofitInterface
    var compositeDisposable = CompositeDisposable()
    override fun getRequest() {
        api = ApiRetrofitClient.getApiRetrofitClient()!!.create(ApiRetrofitInterface::class.java)
        compositeDisposable.add(
            api.getPopular(Values.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { printResult(it) },
                    {
                        it.printStackTrace()
                        sendErrResult()
                    },
                    { println("Done!") }
                )

        )
    }

    private fun printResult(result: MovieResponceModel) {
        presenter.returnResultPopular(result.results)
    }

    override fun sendErrResult() {
        presenter.returnErrResult()
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}