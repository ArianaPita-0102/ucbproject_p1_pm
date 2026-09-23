package edu.ucb.project.profile.data.repository

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.movies.domain.model.MovieModel
import edu.ucb.project.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl : ProfileRepository {

    override suspend fun getProfile(): Result<UserModel> =
        Result.success(
            UserModel(id = "1", fullName = "Usuario de Prueba", email = Email("test@ucb.edu.bo"))
        )

    override suspend fun getFavoriteMovies(): Result<List<MovieModel>> =
        Result.success(emptyList())

    override suspend fun logout(): Result<Unit> = Result.success(Unit)
}