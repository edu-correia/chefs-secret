package com.educorreia.chefssecrets.core.ui.auth

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class DefaultAuthenticator : Authenticator {
    private val _authActions = Channel<AuthAction>()
    override val authActions = _authActions.receiveAsFlow()

    override suspend fun loginWithGoogle() {
        _authActions.send(AuthAction.LoginWithGoogle)
    }

    override suspend fun logout() {
        _authActions.send(AuthAction.Logout)
    }
}