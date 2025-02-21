package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.educorreia.chefssecrets.core.ui.composables.InnerScaffold
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.theme.SystemBarColor
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.CreateRecipeRoute
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.Header
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.NewRecipeButton
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.RecipesList
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipesListScreenRoot(
    navController: NavController,
    viewModel: RecipesListViewModel = koinViewModel<RecipesListViewModel>(),
) {
    val uiState = viewModel.uiState.collectAsState()

    RecipesListScreen(uiState.value, { navController.navigate(CreateRecipeRoute) })
}

@Composable
fun RecipesListScreen(
    uiState: RecipesListUiState,
    onNavigate: () -> Unit
) {
    SystemBarColor(color = AppTheme.colorScheme.secondary)

    InnerScaffold (
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            NewRecipeButton (
                onClick = {
                    onNavigate()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
        ) {
            Header()
            RecipesList(uiState.recipesList)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    AppTheme {
        RecipesListScreen(
            RecipesListUiState(
                listOf(
                    RecipeItem(id = "123", title = "Caesar's salad", description = "Lorem ipsum dolor asit met."),
                    RecipeItem(id = "456", title = "Mac & Cheese", description = "Delicious combination of macaroni and parmesan cheese."),
                )
            ),
            onNavigate = {}
        )
    }
}