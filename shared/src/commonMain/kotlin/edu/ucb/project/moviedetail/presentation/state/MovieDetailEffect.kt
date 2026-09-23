package edu.ucb.project.moviedetail.presentation.state
interface MovieDetailEffect {
    data object NavigateToReview : MovieDetailEffect
    data class ShowError(val message: String) : MovieDetailEffect
}