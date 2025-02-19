package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.educorreia.chefssecrets.ui.theme.AppTheme
import com.educorreia.chefssecrets.ui.theme.SystemBarColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailsScreenRoot(
    viewModel: RecipeDetailsViewModel = koinViewModel<RecipeDetailsViewModel>(),
    arguments: RecipeDetailsRoute
) {
    val uiState = viewModel.uiState.collectAsState()

    RecipeDetailsScreen(uiState.value, arguments)
}

@Composable
fun RecipeDetailsScreen(uiState: RecipeDetailsUiState, arguments: RecipeDetailsRoute) {
    SystemBarColor(
        color = AppTheme.colorScheme.secondary,
        isLightIcons = false
    )

    Text("TODO: Implement details screen for each of the " +
            "${uiState.test} items")
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    AppTheme {
        RecipeDetailsScreen(
            RecipeDetailsUiState(5),
            RecipeDetailsRoute("1234")
        )
    }
}
