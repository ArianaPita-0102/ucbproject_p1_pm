package edu.ucb.project.profile.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProfileEditScreen(navController: NavHostController) {
    var fullName by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp)) {
        TextButton(onClick = { navController.popBackStack() }) { Text("← Volver") }

        Text("Editar Perfil", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nombre completo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Guardar") }
    }
}