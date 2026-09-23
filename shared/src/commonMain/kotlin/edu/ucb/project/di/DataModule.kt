package edu.ucb.project.di

import edu.ucb.project.profile.data.repository.ProfileRepositoryImpl
import edu.ucb.project.profile.domain.repository.ProfileRepository
import edu.ucb.project.signin.data.repository.AuthRepositoryImpl
import edu.ucb.project.signin.domain.repository.AuthRepository
import org.koin.dsl.module
import edu.ucb.project.UserSearch.data.datasource.GithubRemoteDataSource
import edu.ucb.project.UserSearch.data.repository.GithubRepositoryImpl
import edu.ucb.project.UserSearch.data.service.GitHubApiService
import edu.ucb.project.UserSearch.domain.repository.GithubRepository

val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository> { GithubRepositoryImpl(get()) }
}