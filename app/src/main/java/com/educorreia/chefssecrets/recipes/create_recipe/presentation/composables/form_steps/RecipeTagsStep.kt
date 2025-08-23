package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun RecipeTagsStep(
    goToNextStep: () -> Unit
) {
    Text("Recipe tags")
    Button(onClick = goToNextStep) {
        Text("Next page")
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeTagsStepPreview() {
    AppTheme {
        RecipeTagsStep(
            goToNextStep = {}
        )
    }
}
