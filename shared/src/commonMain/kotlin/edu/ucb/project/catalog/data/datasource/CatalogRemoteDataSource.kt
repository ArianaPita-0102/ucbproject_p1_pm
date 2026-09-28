package edu.ucb.project.catalog.data.datasource

import edu.ucb.project.catalog.domain.model.MovieModel

interface CatalogRemoteDataSource {
    suspend fun fetchData(): Result<List<MovieModel>>
}
