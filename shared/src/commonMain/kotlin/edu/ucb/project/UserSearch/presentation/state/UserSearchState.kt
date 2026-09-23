package edu.ucb.project.UserSearch.presentation.state

data class UserSearchState(
    val query: String = "",
    val isAllowed: Boolean = false,
    val isLoading: Boolean = false,
    val results: List<String> = emptyList(),
    val errorMessage: String? = null,
)