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
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.ui.composables.InnerScaffold
import com.educorreia.chefssecrets.core.ui.composables.ShimmerContent
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.theme.SystemBarColor
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.Header
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.NewRecipeButton
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.RecipesList
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.RecipesTitle
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.ShimmerRecipesList
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipesListScreenRoot(
    viewModel: RecipesListViewModel = koinViewModel<RecipesListViewModel>(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val currentUser = viewModel.currentUser.collectAsState()

    RecipesListScreen(uiState.value, viewModel::onEvent, currentUser.value)
}

@Composable
fun RecipesListScreen(
    uiState: RecipesListUiState,
    onEvent: (RecipesListEvent) -> Unit,
    currentUser: User?
) {
    SystemBarColor(color = AppTheme.colorScheme.secondary)

    InnerScaffold (
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            NewRecipeButton (
                onClick = {
                    onEvent(RecipesListEvent.GoToCreateRecipePage)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
        ) {
            Header(
                userFirstName = currentUser?.getFirstName(),
                userPhotoUrl = currentUser?.photoUrl
            )

            RecipesTitle()

            ShimmerContent(
                isLoading = uiState.isLoading,
                contentBeforeLoading = {
                    ShimmerRecipesList()
                },
                contentAfterLoading = {
                    RecipesList(uiState.recipesList)
                }
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    AppTheme {
        RecipesListScreen(
            uiState = RecipesListUiState(
                isLoading = false,
                recipesList = listOf(
                    RecipeItem(id = "123", title = "Caesar's salad", description = "Lorem ipsum dolor asit met.", photoUrl = "https://i.imgur.com/R0eBtWi.png"),
                    RecipeItem(id = "456", title = "Mac & Cheese", description = "Delicious combination of macaroni and parmesan cheese.", photoUrl = "https://i.imgur.com/R0eBtWi.png"),
                )
            ),
            onEvent = {},
            currentUser = User(
                id = "1234",
                name = "John Doe",
                email = "test@mail.com",
                photoUrl = "987654321",
                phoneNumber = null
            )
        )
    }
}