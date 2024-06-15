package com.example.movie.presentation.movie_detail
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.use_case.get_movies.GetMovieDetailUseCase
import com.example.movie.presentation.movies.MoviesState
import com.example.movie.util.Constant.IMDB_ID
import com.example.movie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    stateHandle: SavedStateHandle,
): ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String) {
        getMovieDetailUseCase.executeGetMovieDetail(imdbId).onEach {
            when(it) {
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Hata")
                }
                is Resource.Success -> {
                    _state.value = MovieDetailState(movieDetail = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}