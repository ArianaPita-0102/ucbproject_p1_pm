package edu.ucb.project.register.presentation.state

interface RegisterEffect {
    data object NavigateToLogin : RegisterEffect
    data class ShowError(val message: String) : RegisterEffect
}