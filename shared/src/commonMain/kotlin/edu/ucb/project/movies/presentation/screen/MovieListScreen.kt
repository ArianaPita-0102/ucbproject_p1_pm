package edu.ucb.project.movies.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.ucb.project.movies.domain.model.MovieModel
import edu.ucb.project.movies.presentation.composable.MovieListItem
import edu.ucb.project.movies.presentation.state.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClick: (MovieModel) -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Películas Populares", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.query,
            onValueChange = viewModel::onQueryChange,
            label = { Text("Buscar películas...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        when {
            state.isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            state.errorMessage != null -> Text(
                text = state.errorMessage ?: "",
                color = MaterialTheme.colorScheme.error
            )
            else -> LazyColumn {
                items(state.movies) { movie ->
                    MovieListItem(movie = movie, onClick = onMovieClick)
                }
            }
        }
    }
}
