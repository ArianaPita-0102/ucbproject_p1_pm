package edu.ucb.project.catalog.presentation.state

import edu.ucb.project.catalog.domain.model.MovieModel

data class CatalogState(
    val movies: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
