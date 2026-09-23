package edu.ucb.project.UserSearch.data.mapper

import edu.ucb.project.UserSearch.data.dto.UserInfoDto
import edu.ucb.project.UserSearch.domain.model.UserInfoModel

fun UserInfoDto.toDomain(): UserInfoModel = UserInfoModel(
    alias = login ?: "",
    email = email ?: "",
    company = company ?: "",
    avatarUrl = avatarUrl ?: "",
)