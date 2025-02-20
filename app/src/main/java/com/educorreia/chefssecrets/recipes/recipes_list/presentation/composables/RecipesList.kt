package com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun RecipesList(items: List<RecipeItem>) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .background(AppTheme.colorScheme.background)
    ) {
        item {
            Column {
                Spacer(modifier = Modifier.height(16.dp))

                Text("My recipes")

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        items(items.size) { index ->
            val recipe = items[index]
            RecipeTile(recipe)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListPreview() {
    AppTheme {
        RecipesList(
            listOf(
                RecipeItem(id = "123", title = "Caesar's salad", description = "Lorem ipsum dolor asit met."),
                RecipeItem(id = "456", title = "Mac & Cheese", description = "Delicious combination of macaroni and parmesan cheese."),
            )
        )
    }
}