package com.educorreia.chefssecrets.core.ui.auth

import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
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

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    override val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _userState = MutableStateFlow<UserState>(
        UserState(
            currentUser = userAuthService.getAuthenticatedUser(),
            isLoggedIn = userAuthService.isUserAuthenticated()
        )
    )
    override val userState: StateFlow<UserState> = _userState.asStateFlow()

    init {
        userAuthService.addAuthStateListener { user ->
            _authState.value = if (user == null) {
                AuthState.Unauthenticated
            } else {
                AuthState.Authenticated
            }
            _userState.value = _userState.value.copy(
                currentUser = user,
                isLoggedIn = user != null
            )
        }
    }

    override suspend fun loginWithGoogle() {
        _authActions.send(AuthAction.LoginWithGoogle)
    }

    override suspend fun logout() {
        _authActions.send(AuthAction.Logout)
    }
}