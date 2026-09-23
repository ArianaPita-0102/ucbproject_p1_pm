package edu.ucb.project.register.presentation.state

data class RegisterState(
    val fullName: String = "",
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val confirmPassword: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val registerError: String? = null,
    val registerSuccess: Boolean = false,
)
