package com.example.studyapplication.MovieModels

class MovieResponceModel(
    var page: Int,
    var total_results: Int,
    var total_pages: Int,
    var results: ArrayList<MovieModel>
) {
}