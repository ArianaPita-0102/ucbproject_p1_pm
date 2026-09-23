package edu.ucb.project.moviedetail.domain.model

import edu.ucb.project.movies.domain.vo.MovieId
import edu.ucb.project.movies.domain.vo.PosterPath

data class MovieDetailModel(
    val id: MovieId,
    val title: String,
    val description: String,
    val posterPath: PosterPath,
    val cast: List<String> = emptyList(),
    val criticRating: Double = 0.0,
)
