package com.example.movie.data.repository
import com.example.movie.data.remote.MovieAPI
import com.example.movie.data.remote.dto.MovieDetailDto
import com.example.movie.data.remote.dto.MoviesDto
import com.example.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieAPI): MovieRepository
{
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchMovie = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieList(imdbId = imdbId)
    }
}