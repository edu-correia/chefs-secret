package com.educorreia.chefssecrets.core.ui.auth

import com.educorreia.chefssecrets.core.data.domain.models.User

data class UserState(
    val currentUser: User? = null,
    val isLoggedIn: Boolean = false,
)
