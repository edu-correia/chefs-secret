package com.educorreia.chefssecrets.core.ui.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class DefaultNavigator(
    override val startDestination: Route
): Navigator {
    private val _navigationActions = Channel<NavigationAction>()
    override val navigationActions = _navigationActions.receiveAsFlow()

    override suspend fun navigate(destination: Route) {
        _navigationActions.send(NavigationAction.Navigate(destination))
    }

    override suspend fun goBack() {
        _navigationActions.send(NavigationAction.GoBack)
    }
}
