package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.NextStepButton
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.TransparentTextField

@Composable
fun RecipeTitleAndDescStep(
    goToNextStep: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(24.dp)
            .imePadding()
    ) {
        TransparentTextField(
            label = "Title",
            summary = "This name should reflect how unique and special your recipe is.",
            example = "Mac & Cheese",
            value = "Caesar's salad",
            onChange = {}
        )
        Spacer(Modifier.height(40.dp))
        TransparentTextField(
            label = "Description",
            summary = "This description should describe the details of this recipe.",
            example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin non  volutpat sapien. Sed tincidunt mauris vitae neque dignissim, non congue.",
            value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin non  volutpat sapien. Sed tincidunt mauris vitae neque dignissim, non congue.",
            onChange = {}
        )
        Spacer(Modifier.weight(1f))
        Row {
            NextStepButton(
                text = "Add recipe media",
                onClick = { goToNextStep() }
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeTitleAndDescStepPreview() {
    AppTheme {
        RecipeTitleAndDescStep(
            goToNextStep = {},
        )
    }
}
