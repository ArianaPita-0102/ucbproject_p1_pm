package edu.ucb.project.profile.presentation.state

sealed interface ProfileEvent {
    data object LoadProfile : ProfileEvent
    data object LogoutClicked : ProfileEvent
    data object EditProfileClicked : ProfileEvent
    data object SearchUsersClicked : ProfileEvent
}