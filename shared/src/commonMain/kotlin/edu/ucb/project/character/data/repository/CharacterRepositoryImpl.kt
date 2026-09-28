package edu.ucb.project.character.data.repository

import edu.ucb.project.character.data.datasource.CharacterRemoteDataSource
import edu.ucb.project.character.domain.model.CharacterModel
import edu.ucb.project.character.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val dataSource: CharacterRemoteDataSource
) : CharacterRepository {
    override suspend fun getCharacters(): Result<List<CharacterModel>> = dataSource.fetchData()
}
