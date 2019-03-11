package com.example.studyapplication.MovieModels

class MovieModel(
    var vote_count: Int,
    var id: Int,
    var video: Boolean,
    var vote_average: Float,
    var title: String,
    var popularity: Float,
    var poster_path: String,
    var original_language: String,
    var original_title: String,
    var backdrop_path: String,
    var adult: Boolean,
    var overview: String,
    var release_date: String
) {
}