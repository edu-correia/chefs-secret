package com.educorreia.chefssecrets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsRoute
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsViewModel
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListRoute
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListScreen
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListViewModel
import com.educorreia.chefssecrets.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

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
                            val viewModel = koinViewModel<RecipesListViewModel>()
                            val items = viewModel.uiState.collectAsState()

                            RecipesListScreen(items.value)
                        }

                        composable<RecipeDetailsRoute> {
                            val viewModel = koinViewModel<RecipeDetailsViewModel>()
                            val args = it.toRoute<RecipeDetailsRoute>()
                            Text("TODO: Implement details screen for each of the " +
                                    "${viewModel.uiState.value} items")
                        }
                    }
                }
            }
        }
    }
}
