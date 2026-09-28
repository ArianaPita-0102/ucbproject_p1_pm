package edu.ucb.project.character.data.datasource

import edu.ucb.project.character.domain.model.CharacterModel

interface CharacterRemoteDataSource {
    suspend fun fetchData(): Result<List<CharacterModel>>
}
