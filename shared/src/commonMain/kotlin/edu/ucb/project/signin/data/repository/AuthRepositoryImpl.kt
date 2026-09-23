package edu.ucb.project.signin.data.repository

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.core.domain.vo.Password
import edu.ucb.project.signin.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: Email, password: Password): Result<UserModel> {
        return if (email.value == "test@ucb.edu.bo" && password.value == "123456") {
            Result.success(UserModel(id = "1", fullName = "Usuario de Prueba", email = email))
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }
}