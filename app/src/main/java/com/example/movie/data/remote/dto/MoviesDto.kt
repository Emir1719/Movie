package com.example.movie.data.remote.dto
import com.example.movie.domain.model.Movie

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

fun MoviesDto.toMovieList(): List<Movie> {
    return Search.map {
        Movie(it.Poster, it.Title, it.Year, it.imdbID)
    }
}