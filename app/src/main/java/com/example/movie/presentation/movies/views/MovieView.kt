package com.example.movie.presentation.movies.views
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movie.presentation.View
import com.example.movie.presentation.movies.MoviesEvent
import com.example.movie.presentation.movies.MoviesViewModel

@Composable
fun MovieView(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = { MovieAppBar() },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.DarkGray)
                    .padding(paddingValues)
            ) {
                Column {
                    MovieSearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                    ) {
                        viewModel.onEvent(MoviesEvent.Search(it))
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.movies) { movie ->
                            MovieListItem(movie = movie) { selectedMovie ->
                                navController.navigate(View.MovieDetailView.route + "/${selectedMovie.imdbID}")
                            }
                        }
                    }
                }
            }
        }
    )
}