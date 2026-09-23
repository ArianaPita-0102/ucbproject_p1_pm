package edu.ucb.project.UserSearch.domain.repository

import edu.ucb.project.UserSearch.domain.model.UserInfoModel

interface GithubRepository {
    suspend fun findByAlias(alias: String): Result<UserInfoModel>
}