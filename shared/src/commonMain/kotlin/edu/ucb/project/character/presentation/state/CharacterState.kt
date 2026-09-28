package edu.ucb.project.character.presentation.state

import edu.ucb.project.character.domain.model.CharacterModel

data class CharacterState(
    val characters: List<CharacterModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
