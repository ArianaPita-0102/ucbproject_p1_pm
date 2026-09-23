package edu.ucb.project.signin.domain.repository

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.core.domain.vo.Password
interface AuthRepository {
    suspend fun login(email: Email, password: Password): Result<UserModel>
}
