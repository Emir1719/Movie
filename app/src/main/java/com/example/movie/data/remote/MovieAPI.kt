package com.example.movie.data.remote
import com.example.movie.data.remote.dto.MovieDetailDto
import com.example.movie.data.remote.dto.MoviesDto
import com.example.movie.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET(".") // Base url'den sonra link devam etmiyor. Sadece parametreler başlıyor
    suspend fun getMovies(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("s") searchMovie: String
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieList(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("i") imdbId: String
    ) : MovieDetailDto
}