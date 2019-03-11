package com.example.studyapplication.MovieModels

class MovieVideoModel(
    val id: String,
    val results: ArrayList<Result>
) {
    class Result(
        val id: String,
        val iso_639_1: String,
        val iso_3166_1: String,
        val key: String,
        val name: String,
        val site: String,
        val size: Int,
        val type: String
    )
}