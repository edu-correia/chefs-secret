package com.educorreia.chefssecrets.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.educorreia.chefssecrets.core.ui.utils.ObserveAsEvents

@Composable
fun NavigatorSetup(
    navigator: Navigator,
    navController: NavController
) {
    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when(action) {
            is NavigationAction.Navigate -> navController.navigate(action.destination)
            NavigationAction.GoBack -> navController.navigateUp()
        }
    }
}
