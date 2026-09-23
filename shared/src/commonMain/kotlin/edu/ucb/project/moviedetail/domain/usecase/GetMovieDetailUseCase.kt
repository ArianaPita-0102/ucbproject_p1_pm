package edu.ucb.project.moviedetail.domain.usecase

import edu.ucb.project.movies.domain.vo.MovieId
import edu.ucb.project.moviedetail.domain.model.MovieDetailModel
import edu.ucb.project.moviedetail.domain.repository.MovieDetailRepository

class GetMovieDetailUseCase(
    private val repository: MovieDetailRepository
) {
    suspend operator fun invoke(id: MovieId): Result<MovieDetailModel> {
        return repository.getMovieDetail(id)
    }
}
