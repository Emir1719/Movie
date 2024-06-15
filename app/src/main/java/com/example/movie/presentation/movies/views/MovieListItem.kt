package com.example.movie.presentation.movies.views
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movie.domain.model.Movie

@Composable
fun MovieListItem(
    movie: Movie,
    onTab: (Movie) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onTab(movie)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = movie.Poster),
            contentDescription = movie.Title,
            modifier = Modifier
                .size(200.dp, 230.dp)
                .padding(vertical = 20.dp)
                .clip(RectangleShape)
        )
        Column(
            modifier = Modifier.align(CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ItemText(
                text = movie.Title,
                style = MaterialTheme.typography.titleLarge
            )
            ItemText(
                text = movie.Year,
                style = MaterialTheme.typography.titleSmall
            )
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
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        color = Color.White,
    )
}