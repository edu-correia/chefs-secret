package com.educorreia.chefssecrets.core.ui.splash

sealed interface SplashScreenAction {
    object NavigateToLogin: SplashScreenAction
    object NavigateToAuthenticatedRoute: SplashScreenAction
}