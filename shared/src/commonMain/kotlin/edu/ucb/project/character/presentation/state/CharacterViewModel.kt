package edu.ucb.project.character.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.character.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CharacterEffect>()
    val effect = _effect.asSharedFlow()

    init {
        emitEvent(CharacterEvent.OnLoad)
    }

    private fun emitEffect(effect: CharacterEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }

    fun emitEvent(event: CharacterEvent) {
        when (event) {
            CharacterEvent.OnLoad -> loadCharacters()
            CharacterEvent.OnRetry -> loadCharacters()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getCharactersUseCase()
                .onSuccess { characters ->
                    _state.update { it.copy(isLoading = false, characters = characters) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                    emitEffect(CharacterEffect.ShowToast(error.message ?: "Error al cargar personajes de Star Wars"))
                }
        }
    }
}
