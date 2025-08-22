package com.educorreia.chefssecrets.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
import com.educorreia.chefssecrets.core.ui.auth.AuthState
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.auth.LoginSetup
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.NavigatorSetup
import com.educorreia.chefssecrets.core.ui.navigation.Route.*
import com.educorreia.chefssecrets.core.ui.scaffold.IGNORE_WINDOW_INSETS
import com.educorreia.chefssecrets.core.ui.scaffold.LocalScaffoldConfiguration
import com.educorreia.chefssecrets.core.ui.scaffold.ScaffoldConfiguration
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbar
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbarVisuals
import com.educorreia.chefssecrets.core.ui.snackbar.LocalSnackbarHostState
import com.educorreia.chefssecrets.core.ui.splash.SplashScreenRoot
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.login.presentation.LoginScreenRoot
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.CreateRecipeScreenRoot
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsScreenRoot
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListScreenRoot
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        val authenticator = get<Authenticator>()
        splashScreen.setKeepOnScreenCondition {
            authenticator.authState.value == AuthState.Loading
        }

        enableEdgeToEdge()
        setContent {
            AppTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                var scaffoldConfig by remember { mutableStateOf(ScaffoldConfiguration()) }
                val setScaffoldConfiguration: (ScaffoldConfiguration) -> Unit = {scaffoldConfig = it}

                CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
                    Scaffold(
                        contentWindowInsets = IGNORE_WINDOW_INSETS,
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState) { data ->
                                val visuals = data.visuals as CustomSnackbarVisuals
                                CustomSnackbar(visuals)
                            }
                        },
                        topBar = scaffoldConfig.topBar,
                        floatingActionButton = scaffoldConfig.floatingActionButton,
                        floatingActionButtonPosition = scaffoldConfig.floatingActionButtonPosition
                    ) { innerPadding ->
                        val navController = rememberNavController()

                        val navigator = koinInject<Navigator>()
                        val userAuthService = koinInject<UserAuthService>()

                        LoginSetup(authenticator, userAuthService, navigator, scope)
                        NavigatorSetup(navigator, navController)

                        CompositionLocalProvider(
                            LocalScaffoldConfiguration provides setScaffoldConfiguration
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = navigator.startDestination,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .consumeWindowInsets(innerPadding)
                            ) {
                                composable<SplashScreenRoute> {
                                    SplashScreenRoot()
                                }

                                composable<RecipesListRoute> {
                                    RecipesListScreenRoot()
                                }

                                composable<RecipeDetailsRoute> {
                                    val args = it.toRoute<RecipeDetailsRoute>()
                                    RecipeDetailsScreenRoot(arguments = args)
                                }

                                composable<CreateRecipeRoute> {
                                    CreateRecipeScreenRoot()
                                }

                                composable<LoginRoute> {
                                    LoginScreenRoot()
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
