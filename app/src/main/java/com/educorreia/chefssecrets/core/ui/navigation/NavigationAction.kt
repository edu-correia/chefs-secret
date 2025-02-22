package com.educorreia.chefssecrets.core.ui.navigation

sealed interface NavigationAction {
    data class Navigate(val destination: Route): NavigationAction

    data object GoBack: NavigationAction
}