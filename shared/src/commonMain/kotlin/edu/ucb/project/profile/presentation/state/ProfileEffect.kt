package edu.ucb.project.profile.presentation.state

sealed interface ProfileEffect {
    data object NavigateToLogin : ProfileEffect
    data object NavigateToEdit : ProfileEffect
    data object NavigateToUserSearch : ProfileEffect
    data object NavigateToCatalog : ProfileEffect
    data object NavigateToCharacters : ProfileEffect
    data class ShowError(val message: String) : ProfileEffect
}