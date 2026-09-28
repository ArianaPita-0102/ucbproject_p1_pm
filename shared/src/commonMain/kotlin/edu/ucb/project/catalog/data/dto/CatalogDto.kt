package edu.ucb.project.catalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(
    val page: Int = 0,
    val results: List<MovieDto> = emptyList(),
)
