package com.educorreia.chefssecrets.core.ui.navigation

import kotlinx.coroutines.flow.Flow

interface Navigator {
    val startDestination: Route
    val navigationActions: Flow<NavigationAction>

    suspend fun navigate(destination: Route)

    suspend fun goBack()
}