package edu.ucb.project.profile.domain.repository

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.movies.domain.model.MovieModel

interface ProfileRepository {
    suspend fun getProfile(): Result<UserModel>
    suspend fun getFavoriteMovies(): Result<List<MovieModel>>
    suspend fun logout(): Result<Unit>
}
