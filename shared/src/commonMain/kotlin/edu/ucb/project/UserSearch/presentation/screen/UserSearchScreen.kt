package edu.ucb.project.UserSearch.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.ucb.project.UserSearch.presentation.state.UserSearchEffect
import edu.ucb.project.UserSearch.presentation.state.UserSearchEvent
import edu.ucb.project.UserSearch.presentation.state.UserSearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserSearchScreen(
    navController: NavHostController,
    viewModel: UserSearchViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is UserSearchEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp)) {
            TextButton(onClick = { navController.popBackStack() }) { Text("← Volver") }

            Text("Buscar usuario de GitHub", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = state.query,
                onValueChange = { viewModel.emitEvent(UserSearchEvent.OnQueryChange(it)) },
                label = { Text("Alias (ej. octocat)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.isAllowed,
                    onCheckedChange = { viewModel.emitEvent(UserSearchEvent.OnAllowChange) }
                )
                Text("Habilitar búsqueda")
            }

            Button(
                onClick = { viewModel.emitEvent(UserSearchEvent.OnSearchClick) },
                enabled = state.isAllowed && !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Buscar") }

            Spacer(Modifier.height(16.dp))

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            state.errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            state.user?.let { user ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text(user.alias, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Text("Email: ${user.email.ifBlank { "No público" }}")
                        Text("Empresa: ${user.company.ifBlank { "—" }}")
                        Text("Avatar: ${user.avatarUrl}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}