package edu.ucb.project.catalog.data.repository

import edu.ucb.project.catalog.data.datasource.CatalogRemoteDataSource
import edu.ucb.project.catalog.domain.model.MovieModel
import edu.ucb.project.catalog.domain.repository.CatalogRepository

class CatalogRepositoryImpl(
    private val dataSource: CatalogRemoteDataSource
) : CatalogRepository {
    override suspend fun getMovies(): Result<List<MovieModel>> = dataSource.fetchData()
}
