package edu.ucb.project.UserSearch.presentation.state

sealed interface UserSearchEvent {
    data class OnQueryChange(val value: String) : UserSearchEvent
    data object OnAllowChange : UserSearchEvent
    data object OnSearchClick : UserSearchEvent
}