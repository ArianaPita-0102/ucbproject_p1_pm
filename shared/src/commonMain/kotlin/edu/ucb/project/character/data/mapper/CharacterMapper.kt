package edu.ucb.project.character.data.mapper

import edu.ucb.project.character.data.dto.CharacterDto
import edu.ucb.project.character.data.dto.CharacterPageDto
import edu.ucb.project.character.domain.model.CharacterModel

private const val DESCONOCIDO = "Desconocido"

private fun String?.orDesconocido(): String =
    if (this.isNullOrBlank() || this == "unknown" || this == "n/a") DESCONOCIDO else this

private fun String?.toMeasure(suffix: String): String {
    val value = this.orDesconocido()
    val numeric = value.toIntOrNull()
    return if (numeric != null) "$value$suffix" else value
}

fun CharacterDto.toModel(): CharacterModel = CharacterModel(
    name = name.orDesconocido(),
    height = height.toMeasure(" cm"),
    mass = mass.toMeasure(" kg"),
    hairColor = hairColor.orDesconocido(),
    skinColor = skinColor.orDesconocido(),
    eyeColor = eyeColor.orDesconocido(),
    gender = gender.orDesconocido(),
)

fun CharacterPageDto.toModel(): List<CharacterModel> = results.map { it.toModel() }
