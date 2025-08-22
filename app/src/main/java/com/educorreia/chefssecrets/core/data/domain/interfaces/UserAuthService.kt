package com.educorreia.chefssecrets.core.data.domain.interfaces

import android.content.Context
import com.educorreia.chefssecrets.core.data.domain.models.User

interface UserAuthService {
    // Login and logout
    suspend fun loginWithGoogle(context: Context, onLogin: () -> Unit = {})
    suspend fun logout(context: Context, onLogout: () -> Unit = {})

    // Authenticated user
    fun isUserAuthenticated(): Boolean
    fun getAuthenticatedUser(): User?
    fun addAuthStateListener(onAuthStateChanged: (User?) -> Unit): Unit
}