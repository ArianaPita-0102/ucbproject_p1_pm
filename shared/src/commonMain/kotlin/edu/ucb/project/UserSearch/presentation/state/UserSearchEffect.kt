package edu.ucb.project.UserSearch.presentation.state

sealed interface UserSearchEffect {
    data class ShowError(val message: String) : UserSearchEffect
}