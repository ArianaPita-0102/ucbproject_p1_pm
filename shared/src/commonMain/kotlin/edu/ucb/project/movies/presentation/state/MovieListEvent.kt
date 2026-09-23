package edu.ucb.project.movies.presentation.state

import edu.ucb.project.movies.domain.vo.MovieId
interface MovieListEvent {
    data class QueryChanged(val value: String) : MovieListEvent
    data object LoadMovies : MovieListEvent
    data class MovieClicked(val movieId: MovieId) : MovieListEvent
}