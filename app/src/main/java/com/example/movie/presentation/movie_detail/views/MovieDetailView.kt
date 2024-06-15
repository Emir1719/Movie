package com.example.movie.presentation.movie_detail.views
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movie.presentation.movie_detail.MovieDetailViewModel

@Composable
fun MovieDetailView(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        state.movieDetail?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it.Poster),
                contentDescription = it.Title,
                modifier = Modifier
                    .size(400.dp, 300.dp)
                    .padding(vertical = 20.dp)
                    .clip(RectangleShape)
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ItemText(
                    text = it.Title,
                    style = MaterialTheme.typography.headlineSmall
                )
                ItemText(
                    text = "IMDB: ${it.imdbRating}",
                    style = MaterialTheme.typography.titleMedium
                )
                ItemText(
                    text = it.Year,
                    style = MaterialTheme.typography.titleMedium
                )
                ItemText(
                    text = it.Actors,
                    style = MaterialTheme.typography.titleMedium
                )
                ItemText(
                    text = it.Released,
                    style = MaterialTheme.typography.titleMedium
                )
                ItemText(
                    text = it.Type,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun ItemText(
    text: String,
    style: TextStyle,
) {
    Text(
        text = text,
        style = style,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
    )
}