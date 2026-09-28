package edu.ucb.project.catalog.data.mapper

import edu.ucb.project.catalog.data.dto.MovieDto
import edu.ucb.project.catalog.domain.model.MovieModel

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toModel(): MovieModel = MovieModel(
    title = title ?: "",
    posterPath = POSTER_BASE_URL + (posterPath ?: ""),
)
