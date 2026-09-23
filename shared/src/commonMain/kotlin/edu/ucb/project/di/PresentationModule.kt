package edu.ucb.project.di

import edu.ucb.project.UserSearch.presentation.state.UserSearchViewModel
import edu.ucb.project.profile.presentation.state.ProfileViewModel
import edu.ucb.project.signin.presentation.state.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::UserSearchViewModel)
}