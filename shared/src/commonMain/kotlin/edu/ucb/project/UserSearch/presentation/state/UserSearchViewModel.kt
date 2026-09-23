package edu.ucb.project.UserSearch.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.UserSearch.domain.usecase.SearchUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserSearchViewModel(
    private val searchUseCase: SearchUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserSearchState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UserSearchEffect>()
    val effect = _effect.asSharedFlow()

    private fun emitEffect(effect: UserSearchEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }

    fun emitEvent(event: UserSearchEvent) {
        when (event) {
            is UserSearchEvent.OnQueryChange ->
                _state.update { it.copy(query = event.value) }

            UserSearchEvent.OnAllowChange ->
                _state.update { it.copy(isAllowed = !it.isAllowed) }

            UserSearchEvent.OnSearchClick -> search()
        }
    }

    private fun search() {
        val current = _state.value
        if (current.query.isBlank()) {
            emitEffect(UserSearchEffect.ShowError("Escribe algo para buscar"))
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null, user = null) }
            searchUseCase(current.query)
                .onSuccess { user ->
                    _state.update { it.copy(isLoading = false, user = user) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
        }
    }
}