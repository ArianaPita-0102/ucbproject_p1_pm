package edu.ucb.project.signin.presentation.state

interface LoginEffect {
    data object NavigateToHome : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}