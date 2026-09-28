package edu.ucb.project.character.presentation.state

sealed interface CharacterEffect {
    data class ShowToast(val message: String) : CharacterEffect
}
