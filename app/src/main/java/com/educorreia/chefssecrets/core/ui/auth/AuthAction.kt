package com.educorreia.chefssecrets.core.ui.auth

sealed interface AuthAction {
    data object LoginWithGoogle: AuthAction
    data object Logout: AuthAction
}