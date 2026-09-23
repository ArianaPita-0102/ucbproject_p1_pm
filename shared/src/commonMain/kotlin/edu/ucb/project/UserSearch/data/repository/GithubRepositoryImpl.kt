package edu.ucb.project.UserSearch.data.repository

import edu.ucb.project.UserSearch.data.datasource.GithubRemoteDataSource
import edu.ucb.project.UserSearch.data.mapper.toDomain
import edu.ucb.project.UserSearch.domain.model.UserInfoModel
import edu.ucb.project.UserSearch.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val dataSource: GithubRemoteDataSource
) : GithubRepository {
    override suspend fun findByAlias(alias: String): Result<UserInfoModel> =
        runCatching { dataSource.getUser(alias).toDomain() }
}