package edu.ucb.project.signin.domain.usecase

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.core.domain.vo.Password
import edu.ucb.project.signin.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: Email, password: Password): Result<UserModel> {
        if (!email.isValid()) return Result.failure(IllegalArgumentException("Email inválido"))
        if (!password.isValid()) return Result.failure(IllegalArgumentException("Contraseña muy corta"))
        return repository.login(email, password)
    }
}
