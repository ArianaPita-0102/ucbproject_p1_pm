package edu.ucb.project.UserSearch.data.service

import edu.ucb.project.UserSearch.data.datasource.GithubRemoteDataSource
import edu.ucb.project.UserSearch.data.dto.UserInfoDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GitHubApiService : GithubRemoteDataSource {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getUser(nickname: String): UserInfoDto {
        val response = client.get("https://api.github.com/users/$nickname")
        if (!response.status.isSuccess()) {
            throw Exception("Usuario no encontrado (${response.status.value})")
        }
        return response.body<UserInfoDto>()
    }
}