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

    @Serializable
    object Catalog : NavRoute()

    @Serializable
    object Dollar : NavRoute()

    @Serializable
    object DollarAdd : NavRoute()

    @Serializable
    object Exchange : NavRoute()
}