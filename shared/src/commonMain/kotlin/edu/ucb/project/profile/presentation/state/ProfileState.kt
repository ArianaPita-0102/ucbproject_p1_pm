package edu.ucb.project.profile.presentation.state

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.movies.domain.model.MovieModel

data class ProfileState(
    val user: UserModel? = null,
    val favoriteMovies: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val isLoggedOut: Boolean = false,
    val errorMessage: String? = null,
)
