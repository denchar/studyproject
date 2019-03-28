package com.example.studyapplication.MovieModels

class MovieResponceModel(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: ArrayList<MovieModel>
) {
}