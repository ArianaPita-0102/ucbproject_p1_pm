package edu.ucb.project.UserSearch.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

            Text("Buscar Usuarios", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = state.query,
                onValueChange = { viewModel.emitEvent(UserSearchEvent.OnQueryChange(it)) },
                label = { Text("Nombre") },
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
            } else {
                state.errorMessage?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
                LazyColumn {
                    items(state.results) { name ->
                        Text(name, modifier = Modifier.padding(vertical = 12.dp))
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}