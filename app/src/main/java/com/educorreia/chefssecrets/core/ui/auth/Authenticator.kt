package com.educorreia.chefssecrets.core.ui.auth

import com.educorreia.chefssecrets.core.data.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Authenticator {
    val authActions: Flow<AuthAction>
    val currentUser: StateFlow<User?>

    suspend fun loginWithGoogle()
    suspend fun logout()
    fun refreshAuthenticatedUser()
}