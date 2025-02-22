package com.educorreia.chefssecrets.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.NavigatorSetup
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbar
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbarVisuals
import com.educorreia.chefssecrets.core.ui.snackbar.SnackbarSetup
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.navigation.Route.CreateRecipeRoute
import com.educorreia.chefssecrets.core.ui.navigation.Route.RecipeDetailsRoute
import com.educorreia.chefssecrets.core.ui.navigation.Route.RecipesListRoute
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.CreateRecipeScreenRoot
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsScreenRoot
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListScreenRoot
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                SnackbarSetup(snackbarHostState)

                Box(Modifier.safeDrawingPadding()) {
                    Scaffold (
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState) { data ->
                                val visuals = data.visuals as CustomSnackbarVisuals
                                CustomSnackbar(visuals)
                            }
                        },
                    ) { pad ->
                        val navController = rememberNavController()
                        val navigator = koinInject<Navigator>()

                        NavigatorSetup(navigator, navController)

                        NavHost(
                            navController = navController,
                            startDestination = RecipesListRoute,
                            modifier = Modifier.padding(pad)
                        ) {
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
                        }
                    }
                }
            }
        }
    }
}
