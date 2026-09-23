package edu.ucb.project.UserSearch.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    val login: String? = null,
    val email: String? = null,
    val company: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
)