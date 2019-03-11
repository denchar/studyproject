package com.example.studyapplication.MovieModels

class MovieReviewModel(
    val id: Int,
    val page: Int,
    val results: ArrayList<Result>,
    val total_pages: Int,
    val total_results: Int
) {
    class Result(
        val author: String,
        val content: String,
        val id: String,
        val url: String
    )
}