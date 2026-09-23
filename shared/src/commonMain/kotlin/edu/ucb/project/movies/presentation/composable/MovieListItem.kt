package edu.ucb.project.movies.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucb.project.movies.domain.model.MovieModel

@Composable
fun MovieListItem(
    movie: MovieModel,
    onClick: (MovieModel) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = movie.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Text(text = movie.description, style = MaterialTheme.typography.bodySmall, maxLines = 2)
            Text(text = "★ ${movie.rating}", style = MaterialTheme.typography.labelMedium)
        }
    }
}
