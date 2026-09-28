package edu.ucb.project.catalog.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.catalog.domain.usecase.GetCatalogUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val getCatalogUseCase: GetCatalogUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CatalogState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CatalogEffect>()
    val effect = _effect.asSharedFlow()

    init {
        emitEvent(CatalogEvent.OnLoad)
    }

    private fun emitEffect(effect: CatalogEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }

    fun emitEvent(event: CatalogEvent) {
        when (event) {
            CatalogEvent.OnLoad -> loadCatalog()
            is CatalogEvent.OnMovieClick -> emitEffect(CatalogEffect.ShowToast(event.movie.title))
        }
    }

    private fun loadCatalog() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getCatalogUseCase()
                .onSuccess { movies ->
                    _state.update { it.copy(isLoading = false, movies = movies) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                    emitEffect(CatalogEffect.ShowToast(error.message ?: "Error al cargar el catálogo"))
                }
        }
    }
}
