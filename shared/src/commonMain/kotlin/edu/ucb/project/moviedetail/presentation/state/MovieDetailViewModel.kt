package edu.ucb.project.moviedetail.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.movies.domain.vo.MovieId
import edu.ucb.project.moviedetail.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class MovieDetailViewModel(
    private val getMovieDetail: GetMovieDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state.asStateFlow()

    fun loadMovie(id: MovieId) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getMovieDetail(id)
                .onSuccess { movie ->
                    _state.update { it.copy(isLoading = false, movie = movie) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
        }
    }
}
