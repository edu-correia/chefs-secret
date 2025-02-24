package com.educorreia.chefssecrets.login.presentation

sealed class LoginEvent {
    object LoginWithGoogle: LoginEvent()
}