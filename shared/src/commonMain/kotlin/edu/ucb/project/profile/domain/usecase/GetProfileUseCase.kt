package edu.ucb.project.profile.domain.usecase

import edu.ucb.project.core.domain.model.UserModel
import edu.ucb.project.profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Result<UserModel> = repository.getProfile()
}
