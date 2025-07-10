package com.educorreia.chefssecrets.core.ui.auth

import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
import com.educorreia.chefssecrets.core.data.domain.models.User
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class DefaultAuthenticator(
    private val userAuthService: UserAuthService
) : Authenticator {
    private val _authActions = Channel<AuthAction>()
    override val authActions = _authActions.receiveAsFlow()

    private val _currentUser = MutableStateFlow<User?>(userAuthService.getAuthenticatedUser())
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    override suspend fun loginWithGoogle() {
        _authActions.send(AuthAction.LoginWithGoogle)
    }

    override suspend fun logout() {
        _authActions.send(AuthAction.Logout)
    }

    override fun refreshAuthenticatedUser() {
        _currentUser.value = userAuthService.getAuthenticatedUser()
    }
}