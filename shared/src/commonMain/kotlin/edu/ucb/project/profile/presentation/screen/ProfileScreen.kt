package edu.ucb.project.profile.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.ucb.project.navigation.NavRoute
import edu.ucb.project.profile.presentation.state.ProfileEffect
import edu.ucb.project.profile.presentation.state.ProfileEvent
import edu.ucb.project.profile.presentation.state.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                ProfileEffect.NavigateToEdit -> navController.navigate(NavRoute.ProfileEdit)
                ProfileEffect.NavigateToUserSearch -> navController.navigate(NavRoute.UserSearch)
                ProfileEffect.NavigateToLogin -> navController.navigate(NavRoute.Login) {
                    popUpTo<NavRoute.Profile> { inclusive = true }
                }
                is ProfileEffect.ShowError -> { /* TODO: snackbar con effect.message */ }
            }
        }
    }

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mi Perfil", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        state.user?.let { user ->
            Text(text = user.fullName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Text(text = user.email.value, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.height(24.dp))
        Text(text = "Mis Películas Favoritas", fontWeight = FontWeight.SemiBold)
        Text(text = "${state.favoriteMovies.size} película(s)")

        Spacer(Modifier.height(24.dp))
        OutlinedButton(
            onClick = { viewModel.emitEvent(ProfileEvent.EditProfileClicked) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Editar Perfil") }

        Spacer(Modifier.height(8.dp))
        OutlinedButton(
            onClick = { viewModel.emitEvent(ProfileEvent.SearchUsersClicked) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Buscar Usuarios") }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { viewModel.emitEvent(ProfileEvent.LogoutClicked) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Cerrar Sesión") }
    }
}