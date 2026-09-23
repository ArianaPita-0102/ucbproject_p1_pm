package edu.ucb.project.moviedetail.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucb.project.movies.domain.vo.MovieId
import edu.ucb.project.moviedetail.presentation.state.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: MovieId,
    viewModel: MovieDetailViewModel,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    when {
        state.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        state.errorMessage != null -> Text(
            text = state.errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
        state.movie != null -> {
            val movie = state.movie!!
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(text = movie.description, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(16.dp))
                Text(text = "Reparto Principal", fontWeight = FontWeight.SemiBold)
                Text(text = movie.cast.joinToString(", "))
                Spacer(Modifier.height(16.dp))
                Text(text = "Puntuación de Crítica: ${movie.criticRating}")
                Spacer(Modifier.height(24.dp))
                Button(onClick = { /* TODO: Escribir Reseña */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Escribir Reseña")
                }
            }
        }
    }
}
