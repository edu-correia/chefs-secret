package com.educorreia.chefssecrets.core.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.ui.auth.AuthState
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.Route
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val navigator: Navigator,
    private val authenticator: Authenticator
) : ViewModel() {
    val authState: StateFlow<AuthState> = authenticator.authState

    fun onEvent(event: SplashScreenAction) {
        when (event) {
            SplashScreenAction.NavigateToLogin -> {
                viewModelScope.launch {
                    navigator.navigate(Route.LoginRoute)
                }
            }
            SplashScreenAction.NavigateToAuthenticatedRoute -> {
                viewModelScope.launch {
                    navigator.navigate(Route.RecipesListRoute)
                }
            }
        }
    }
}