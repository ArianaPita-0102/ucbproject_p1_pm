package edu.ucb.project.profile.domain.usecase

import edu.ucb.project.profile.domain.repository.ProfileRepository

class LogoutUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Result<Unit> = repository.logout()
}
