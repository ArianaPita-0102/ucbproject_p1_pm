package edu.ucb.project.movies.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.movies.domain.usecase.GetPopularMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class MovieListViewModel(
    private val getPopularMovies: GetPopularMovies
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    fun onQueryChange(value: String) {
        _state.update { it.copy(query = value) }
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getPopularMovies(_state.value.query)
                .onSuccess { movies ->
                    _state.update { it.copy(isLoading = false, movies = movies) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
        }
    }
}
