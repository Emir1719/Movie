package com.example.movie.domain.use_case.get_movies
import com.example.movie.data.remote.dto.toMovieList
import com.example.movie.domain.model.Movie
import com.example.movie.domain.repository.MovieRepository
import com.example.movie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)

            if (movieList.Response.equals("False")) {
                emit(Resource.Error("Aradığınız film bulunamadı"))
                return@flow
            }

            emit(Resource.Success(movieList.toMovieList()))
        }
        catch (e: IOError) {
            emit(Resource.Error("İnternet bağlantısını kontrol edin"))
        }
        catch (e: Exception) {
            emit(Resource.Error("Bilinmeyen bir hata oluştu"))
        }
        catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Hata"))
        }
    }
}