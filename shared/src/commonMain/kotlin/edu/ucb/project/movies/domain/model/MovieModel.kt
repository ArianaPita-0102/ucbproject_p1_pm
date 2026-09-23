package edu.ucb.project.movies.domain.model

import edu.ucb.project.movies.domain.vo.MovieId
import edu.ucb.project.movies.domain.vo.PosterPath

data class MovieModel(
    val id: MovieId,
    val title: String,
    val description: String,
    val posterPath: PosterPath,
    val rating: Double = 0.0,
)
