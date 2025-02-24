package com.educorreia.chefssecrets.core.data.domain.interfaces

import android.content.Context
import com.educorreia.chefssecrets.core.data.domain.models.User

interface UserAuthService {
    suspend fun loginWithGoogle(context: Context, onLogin: () -> Unit)
    suspend fun logout(context: Context)
    fun isUserAuthenticated(): Boolean
    fun getAuthenticatedUser(): User?
}