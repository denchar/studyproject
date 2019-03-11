package com.example.studyapplication.retrofit


import com.example.studyapplication.MovieModels.*
import com.example.studyapplication.models.MainActivityModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

import java.util.*

interface ApiRetrofitInterface {

    @GET("popular?language=ru")
    fun getPopular(@Query("api_key") key: String): Observable<MovieResponceModel>

    @GET("top_rated?language=ru")
    fun getTopRaited(@Query("api_key") key: String): Observable<MovieResponceModel>

    @GET("{id}/videos?language=ru")
    fun getMovieVideos(@Path("id") id: Int, @Query("api_key") key: String): Observable<MovieVideoModel>

    @GET("{id}/reviews?page=1")
    fun getMovieReview(@Path("id") id: Int, @Query("api_key") key: String): Observable<MovieReviewModel>
}