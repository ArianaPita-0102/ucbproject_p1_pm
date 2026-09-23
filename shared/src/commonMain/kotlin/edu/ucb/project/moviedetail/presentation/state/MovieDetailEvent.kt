package edu.ucb.project.moviedetail.presentation.state

import edu.ucb.project.movies.domain.vo.MovieId
interface MovieDetailEvent {
    data class LoadMovie(val movieId: MovieId) : MovieDetailEvent
    data object WriteReviewClicked : MovieDetailEvent
}