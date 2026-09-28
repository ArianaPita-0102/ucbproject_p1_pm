package edu.ucb.project.catalog.domain.repository

import edu.ucb.project.catalog.domain.model.MovieModel

interface CatalogRepository {
    suspend fun getMovies(): Result<List<MovieModel>>
}
