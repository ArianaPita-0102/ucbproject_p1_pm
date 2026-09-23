package edu.ucb.project.di

import edu.ucb.project.profile.data.repository.ProfileRepositoryImpl
import edu.ucb.project.profile.domain.repository.ProfileRepository
import edu.ucb.project.signin.data.repository.AuthRepositoryImpl
import edu.ucb.project.signin.domain.repository.AuthRepository
import org.koin.dsl.module

val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
}