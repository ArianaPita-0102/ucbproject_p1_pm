package edu.ucb.project.profile.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.profile.domain.usecase.GetProfileUseCase
import edu.ucb.project.profile.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<ProfileEffect>()
    val effects = _effects.asSharedFlow()

    init {
        loadProfile()
    }

    private fun emitEffect(effect: ProfileEffect) {
        viewModelScope.launch { _effects.emit(effect) }
    }

    fun emitEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LoadProfile -> loadProfile()
            ProfileEvent.LogoutClicked -> logout()
            ProfileEvent.EditProfileClicked -> emitEffect(ProfileEffect.NavigateToEdit)
            ProfileEvent.SearchUsersClicked -> emitEffect(ProfileEffect.NavigateToUserSearch)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getProfileUseCase()
                .onSuccess { user ->
                    _state.update { it.copy(isLoading = false, user = user) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                    emitEffect(ProfileEffect.ShowError(error.message ?: "Error al cargar perfil"))
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            logoutUseCase()
                .onSuccess {
                    _state.update { it.copy(isLoading = false, isLoggedOut = true) }
                    emitEffect(ProfileEffect.NavigateToLogin)
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                    emitEffect(ProfileEffect.ShowError(error.message ?: "Error al cerrar sesión"))
                }
        }
    }
}