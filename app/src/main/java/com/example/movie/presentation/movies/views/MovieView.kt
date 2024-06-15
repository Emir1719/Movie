package com.example.movie.presentation.movies.views
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
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
        topBar = { AppBar() },
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
                        hint = "Batman",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(
        title = {
            Text("Movie Search")
        },
        colors = TopAppBarColors(
            containerColor = Color.Black,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White,
            scrolledContainerColor = Color.White,
            titleContentColor = Color.White,
        ),
        /*colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),*/
    )
}