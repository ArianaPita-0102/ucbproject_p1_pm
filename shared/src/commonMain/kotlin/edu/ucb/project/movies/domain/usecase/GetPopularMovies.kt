package edu.ucb.project.movies.domain.usecase

import edu.ucb.project.movies.domain.model.MovieModel
import edu.ucb.project.movies.domain.repository.MovieRepository

class GetPopularMovies(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(query: String = ""): Result<List<MovieModel>> {
        return repository.getPopularMovies(query)
    }
}
