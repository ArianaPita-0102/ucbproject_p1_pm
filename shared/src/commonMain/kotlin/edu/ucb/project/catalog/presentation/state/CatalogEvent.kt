package edu.ucb.project.catalog.presentation.state

import edu.ucb.project.catalog.domain.model.MovieModel

sealed interface CatalogEvent {
    data object OnLoad : CatalogEvent
    data class OnMovieClick(val movie: MovieModel) : CatalogEvent
}
