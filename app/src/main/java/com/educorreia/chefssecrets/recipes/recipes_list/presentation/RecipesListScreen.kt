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
import com.educorreia.chefssecrets.core.ui.composables.ShimmerContent
import com.educorreia.chefssecrets.core.ui.scaffold.PreviewScaffold
import com.educorreia.chefssecrets.core.ui.scaffold.ScaffoldSetup
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeSummaryUIModel
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeUIModel
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.UserHeader
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

    ScaffoldSetup(
        topBar = {
            UserHeader(
                userFirstName = currentUser.value?.getFirstName(),
                userPhotoUrl = currentUser.value?.photoUrl
            )
        },
        floatingActionButton = {
            NewRecipeButton(onClick = {
                viewModel.onEvent(RecipesListAction.GoToCreateRecipePage)
            })
        },
        floatingActionButtonPosition = FabPosition.Center
    )

    RecipesListScreen(uiState.value, viewModel::onEvent, currentUser.value)
}

@Composable
fun RecipesListScreen(
    uiState: RecipesListUiState,
    onEvent: (RecipesListAction) -> Unit,
    currentUser: User?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
    ) {
        RecipesTitle()

        ShimmerContent(
            isLoading = uiState.isLoading,
            contentBeforeLoading = {
                ShimmerRecipesList()
            },
            contentAfterLoading = {
                RecipesList(
                    items = uiState.recipesList,
                    onClick = { id ->
                        onEvent.invoke(RecipesListAction.GoToRecipeDetailsPage(id))
                    }
                )
            }
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    AppTheme {
        PreviewScaffold(
            topBar = {
                UserHeader(
                    userFirstName = "John",
                    userPhotoUrl = "https://i.imgur.com/R0eBtWi.png"
                )
            },
            fab = {
                NewRecipeButton(onClick = {})
            },
            fabPosition = FabPosition.Center
        ) {
            RecipesListScreen(
                uiState = RecipesListUiState(
                    isLoading = false,
                    recipesList = listOf(
                        RecipeSummaryUIModel(id = "123", title = "Caesar's salad", description = "Lorem ipsum dolor asit met.", photoUrl = "https://i.imgur.com/R0eBtWi.png", null),
                        RecipeSummaryUIModel(id = "456", title = "Mac & Cheese", description = "Delicious combination of macaroni and parmesan cheese.", photoUrl = "https://i.imgur.com/R0eBtWi.png", null),
                    )
                ),
                onEvent = {},
                currentUser = User(
                    id = "1234",
                    name = "John Doe",
                    email = "test@mail.com",
                    photoUrl = "987654321",
                )
            )
        }
    }
}