package edu.ucb.project.character.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucb.project.character.domain.model.CharacterModel

@Composable
fun CharacterCard(
    character: CharacterModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = character.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
            )
            Text("Altura: ${character.height}")
            Text("Peso: ${character.mass}")
            Text("Color de cabello: ${character.hairColor}")
            Text("Color de piel: ${character.skinColor}")
            Text("Color de ojos: ${character.eyeColor}")
            Text("Género: ${character.gender}")
        }
    }
}
