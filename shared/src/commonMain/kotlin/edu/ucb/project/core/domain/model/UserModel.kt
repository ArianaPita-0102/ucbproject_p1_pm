package edu.ucb.project.core.domain.model

import edu.ucb.project.core.domain.vo.Email

data class UserModel(
    val id: String,
    val fullName: String,
    val email: Email,
)
