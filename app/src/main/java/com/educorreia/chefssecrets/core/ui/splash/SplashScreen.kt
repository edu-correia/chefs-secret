package com.educorreia.chefssecrets.core.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.educorreia.chefssecrets.core.ui.auth.AuthState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreenRoot(
    viewModel: SplashScreenViewModel = koinViewModel<SplashScreenViewModel>()
) {
    val authState = viewModel.authState.collectAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            AuthState.Unauthenticated -> {
                viewModel.onEvent(SplashScreenAction.NavigateToLogin)
            }
            AuthState.Authenticated -> {
                viewModel.onEvent(SplashScreenAction.NavigateToAuthenticatedRoute)
            }
            AuthState.Loading -> {
                // Do nothing, the splash screen will continue to be visible
            }
        }
    }

    // TODO: Create custom SplashScreen
    // For now, let's use an empty composable because the system will render the default splash
    // screen.
}