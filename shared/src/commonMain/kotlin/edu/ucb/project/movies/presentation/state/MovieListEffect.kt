package edu.ucb.project.movies.presentation.state

import edu.ucb.project.movies.domain.vo.MovieId
interface MovieListEffect {
    data class NavigateToDetail(val movieId: MovieId) : MovieListEffect
    data class ShowError(val message: String) : MovieListEffect
}