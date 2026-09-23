package edu.ucb.project.UserSearch.domain.usecase

import edu.ucb.project.UserSearch.domain.model.UserInfoModel
import edu.ucb.project.UserSearch.domain.repository.GithubRepository

class SearchUserUseCase(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(query: String): Result<UserInfoModel> =
        repository.findByAlias(query.trim())
}