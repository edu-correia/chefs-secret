package com.educorreia.chefssecrets.core.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.Route
import com.educorreia.chefssecrets.core.ui.utils.ObserveAsEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginSetup(
    authenticator: Authenticator,
    userAuthService: UserAuthService,
    navigator: Navigator,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = authenticator.authActions) { action ->
        when (action) {
            is AuthAction.LoginWithGoogle -> {
                scope.launch {
                    userAuthService.loginWithGoogle(context) {
                        scope.launch {
                            navigator.navigate(Route.RecipesListRoute)
                        }
                    }
                }
            }
            is AuthAction.Logout -> {
                scope.launch {
                    userAuthService.logout(context) {
                        scope.launch {
                            navigator.navigate(Route.LoginRoute)
                        }
                    }
                }
            }
        }
    }
}