package com.educorreia.chefssecrets.core.ui.auth

import kotlinx.coroutines.flow.Flow

interface Authenticator {
    val authActions: Flow<AuthAction>

    suspend fun loginWithGoogle()
    suspend fun logout()
}