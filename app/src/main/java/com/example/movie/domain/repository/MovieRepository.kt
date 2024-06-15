package com.example.movie.domain.repository
import com.example.movie.data.remote.dto.MovieDetailDto
import com.example.movie.data.remote.dto.MoviesDto

interface MovieRepository {
    suspend fun getMovies(search: String): MoviesDto
    suspend fun getMovieDetail(imdbId: String): MovieDetailDto
}