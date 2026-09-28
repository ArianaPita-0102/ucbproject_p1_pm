package edu.ucb.project.character.domain.usecase

import edu.ucb.project.character.domain.model.CharacterModel
import edu.ucb.project.character.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): Result<List<CharacterModel>> = repository.getCharacters()
}
