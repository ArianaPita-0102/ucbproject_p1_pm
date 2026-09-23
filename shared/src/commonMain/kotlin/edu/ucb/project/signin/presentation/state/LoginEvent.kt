package edu.ucb.project.signin.presentation.state
interface LoginEvent {
    data class OnEmailChanged(val value: String) : LoginEvent
    data class PasswordChanged(val value: String) : LoginEvent
    data object TogglePasswordVisibility : LoginEvent
    data object Submit : LoginEvent
}