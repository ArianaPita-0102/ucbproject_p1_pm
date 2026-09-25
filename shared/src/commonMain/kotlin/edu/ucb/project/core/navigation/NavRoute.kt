package edu.ucb.project.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    object Login : NavRoute()

    @Serializable
    object Profile : NavRoute()

    @Serializable
    object ProfileEdit : NavRoute()

    @Serializable
    object UserSearch : NavRoute()
}