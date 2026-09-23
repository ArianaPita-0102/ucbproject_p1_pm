package edu.ucb.project.di

import edu.ucb.project.UserSearch.domain.usecase.SearchUserUseCase
import edu.ucb.project.profile.domain.usecase.GetProfileUseCase
import edu.ucb.project.profile.domain.usecase.LogoutUseCase
import edu.ucb.project.signin.domain.usecase.LoginUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::GetProfileUseCase)
    singleOf(::LogoutUseCase)
    singleOf(::SearchUserUseCase)
}