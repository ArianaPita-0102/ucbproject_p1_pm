package edu.ucb.project.UserSearch.presentation.state

import edu.ucb.project.UserSearch.domain.model.UserInfoModel

data class UserSearchState(
    val query: String = "",
    val isAllowed: Boolean = false,
    val isLoading: Boolean = false,
    val user: UserInfoModel? = null,
    val errorMessage: String? = null,
)