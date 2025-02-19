package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.Header
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables.RecipesList
import com.educorreia.chefssecrets.ui.theme.AppTheme
import com.educorreia.chefssecrets.ui.theme.SystemBarColor

@Composable
fun RecipesListScreen(items: List<RecipeItem>) {
    SystemBarColor(
        color = AppTheme.colorScheme.secondary,
        isLightIcons = false
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
    ) {
        Header()
        RecipesList(items)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    AppTheme {
        RecipesListScreen(
            listOf(
                RecipeItem(id = "123", title = "Caesar's salad", description = "Lorem ipsum dolor asit met."),
                RecipeItem(id = "456", title = "Mac & Cheese", description = "Delicious combination of macaroni and parmesan cheese."),
            )
        )
    }
}