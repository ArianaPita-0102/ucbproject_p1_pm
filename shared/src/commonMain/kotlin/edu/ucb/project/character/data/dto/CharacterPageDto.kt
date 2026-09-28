package edu.ucb.project.character.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterPageDto(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<CharacterDto> = emptyList(),
)
