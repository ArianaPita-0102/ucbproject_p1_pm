package edu.ucb.project.character.data.service

import edu.ucb.project.character.data.datasource.CharacterRemoteDataSource
import edu.ucb.project.character.data.dto.CharacterPageDto
import edu.ucb.project.character.data.mapper.toModel
import edu.ucb.project.character.domain.model.CharacterModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val PEOPLE_URL = "https://swapi.dev/api/people/"

class CharacterService : CharacterRemoteDataSource {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun fetchData(): Result<List<CharacterModel>> {
        return try {
            val response: HttpResponse = client.get(PEOPLE_URL)
            if (!response.status.isSuccess()) {
                return Result.failure(Exception("Error al obtener personajes: ${response.status}"))
            }
            val dto = response.body<CharacterPageDto>()
            Result.success(dto.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
