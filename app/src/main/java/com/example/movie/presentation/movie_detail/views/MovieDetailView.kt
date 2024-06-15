package com.example.movie.presentation.movie_detail.views
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movie.domain.model.MovieDetail
import com.example.movie.presentation.movie_detail.MovieDetailViewModel
import com.example.movie.presentation.movies.views.MovieAppBar

@Composable
fun MovieDetailView(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = { MovieAppBar() }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = Color.DarkGray)
        ) {
            state.movieDetail?.let {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter),
                ) {
                    ItemImage(movieDetail = it)
                    ItemText(
                        text = it.Title,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                    )
                    Row {
                        Icon(
                            Icons.Rounded.Star,
                            contentDescription = "icon",
                            tint = Color.Yellow,
                            modifier = Modifier.padding(start = 20.dp, end = 5.dp)
                        )
                        Text(
                            text = "IMDB: ${it.imdbRating}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    ItemText(
                        text = "Country: ${it.Country}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    ItemText(
                        text = "Released Date: ${it.Released}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    ItemText(
                        text = "Actors: ${it.Actors}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    ItemText(
                        text = "Awards: ${it.Awards}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@Composable
private fun ItemText(
    text: String,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Left,
) {
    Text(
        text = text,
        style = style,
        textAlign = textAlign,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
    )
}

@Composable
fun ItemImage(movieDetail: MovieDetail) {
    Image(
        painter = rememberAsyncImagePainter(model = movieDetail.Poster),
        contentDescription = movieDetail.Title,
        modifier = Modifier
            .size(400.dp, 300.dp)
            .padding(vertical = 20.dp)
            .clip(RectangleShape)
    )
}