package edu.ucb.project.character.presentation.state

sealed interface CharacterEvent {
    data object OnLoad : CharacterEvent
    data object OnRetry : CharacterEvent
}
