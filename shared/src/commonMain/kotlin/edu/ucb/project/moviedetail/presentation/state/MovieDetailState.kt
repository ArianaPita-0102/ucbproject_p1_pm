package edu.ucb.project.moviedetail.presentation.state

import edu.ucb.project.moviedetail.domain.model.MovieDetailModel

data class MovieDetailState(
    val movie: MovieDetailModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
