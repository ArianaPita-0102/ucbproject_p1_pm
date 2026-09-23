package edu.ucb.project.signin.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.core.domain.vo.Password
import edu.ucb.project.signin.domain.usecase.LoginUseCase
import edu.ucb.project.signin.presentation.state.LoginEffect
import edu.ucb.project.signin.presentation.state.LoginEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<LoginEffect>()
    val effects = _effects.asSharedFlow()

    private fun emitEffect(effect: LoginEffect) {
        viewModelScope.launch { _effects.emit(effect) }
    }

    fun emitEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged ->
                _state.update { it.copy(email = event.value) }

            is LoginEvent.PasswordChanged ->
                _state.update { it.copy(password = event.value) }

            LoginEvent.TogglePasswordVisibility ->
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }

            LoginEvent.Submit -> submit()
        }
    }

    private fun submit() {
        val current = _state.value

        if (current.email.isBlank()) {
            emitEffect(LoginEffect.ShowError("El correo es obligatorio"))
            return
        }
        if (current.password.isBlank()) {
            emitEffect(LoginEffect.ShowError("La contraseña es obligatoria"))
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            loginUseCase(Email(current.email), Password(current.password))
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                    emitEffect(LoginEffect.NavigateToHome)
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false) }
                    emitEffect(LoginEffect.ShowError(error.message ?: "Error al iniciar sesión"))
                }
        }
    }
}