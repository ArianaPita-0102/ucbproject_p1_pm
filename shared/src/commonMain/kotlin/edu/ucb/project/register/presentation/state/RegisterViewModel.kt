package edu.ucb.project.register.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.core.domain.vo.Email
import edu.ucb.project.core.domain.vo.Password
import edu.ucb.project.register.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onFullNameChange(value: String) {
        _state.update { it.copy(fullName = value) }
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value, emailError = null) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value, passwordError = null) }
    }

    fun onConfirmPasswordChange(value: String) {
        _state.update { it.copy(confirmPassword = value, passwordError = null) }
    }

    fun onRegisterClick() {
        val current = _state.value

        if (current.password != current.confirmPassword) {
            _state.update { it.copy(passwordError = "Las contraseñas no coinciden") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, registerError = null) }
            val result = registerUseCase(
                fullName = current.fullName,
                email = Email(current.email),
                password = Password(current.password)
            )
            result
                .onSuccess {
                    _state.update { it.copy(isLoading = false, registerSuccess = true) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, registerError = error.message) }
                }
        }
    }
}
