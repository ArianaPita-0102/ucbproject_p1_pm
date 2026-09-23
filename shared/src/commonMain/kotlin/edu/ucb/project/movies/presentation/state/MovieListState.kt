package edu.ucb.project.movies.presentation.state

import edu.ucb.project.movies.domain.model.MovieModel
data class MovieListState(
    val query: String = "",
    val movies: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
