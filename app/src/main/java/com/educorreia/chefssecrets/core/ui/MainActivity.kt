package com.educorreia.chefssecrets.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsRoute
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsScreenRoot
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListRoute
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListScreenRoot
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold { pad ->
                    val navController = rememberNavController()
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
                    }
                }
            }
        }
    }
}
