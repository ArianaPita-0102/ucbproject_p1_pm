package edu.ucb.project.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucb.project.UserSearch.presentation.screen.UserSearchScreen
import edu.ucb.project.profile.presentation.screen.ProfileEditScreen
import edu.ucb.project.profile.presentation.screen.ProfileScreen
import edu.ucb.project.signin.presentation.screen.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Login) {

        composable<NavRoute.Login> {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(NavRoute.Profile) {
                        popUpTo<NavRoute.Login> { inclusive = true }
                    }
                }
            )
        }

        composable<NavRoute.Profile> {
            ProfileScreen(navController = navController)
        }

        composable<NavRoute.ProfileEdit> {
            ProfileEditScreen(navController = navController)
        }

        composable<NavRoute.UserSearch> {
            UserSearchScreen(navController = navController)
        }
    }
}