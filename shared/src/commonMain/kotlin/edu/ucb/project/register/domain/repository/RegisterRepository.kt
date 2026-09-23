package edu.ucb.project.register.domain.repository

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.core.domain.vo.Password

interface RegisterRepository {
    suspend fun register(fullName: String, email: Email, password: Password): Result<UserModel>
}
