package edu.ucb.project.character.domain.repository

import edu.ucb.project.character.domain.model.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(): Result<List<CharacterModel>>
}
