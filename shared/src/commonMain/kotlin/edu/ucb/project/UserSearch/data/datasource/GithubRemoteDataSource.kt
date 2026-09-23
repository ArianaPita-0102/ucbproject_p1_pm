package edu.ucb.project.UserSearch.data.datasource

import edu.ucb.project.UserSearch.data.dto.UserInfoDto

interface GithubRemoteDataSource {
    suspend fun getUser(nickname: String): UserInfoDto
}