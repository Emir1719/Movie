package com.example.movie.presentation

sealed class View(val route: String) {
    data object MovieView: View("movie_list")
    data object MovieDetailView: View("movie_detail")
}