package edu.ucb.project.catalog.presentation.state

sealed interface CatalogEffect {
    data class ShowToast(val message: String) : CatalogEffect
}
