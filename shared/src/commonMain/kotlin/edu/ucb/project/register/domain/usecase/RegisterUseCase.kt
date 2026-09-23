package edu.ucb.project.register.domain.usecase

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.core.domain.vo.Password
import edu.ucb.project.register.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(fullName: String, email: Email, password: Password): Result<UserModel> {
        if (fullName.isBlank()) return Result.failure(IllegalArgumentException("El nombre no puede estar vacío"))
        if (!email.isValid()) return Result.failure(IllegalArgumentException("Email inválido"))
        if (!password.isValid()) return Result.failure(IllegalArgumentException("Contraseña muy corta"))
        return repository.register(fullName, email, password)
    }
}
