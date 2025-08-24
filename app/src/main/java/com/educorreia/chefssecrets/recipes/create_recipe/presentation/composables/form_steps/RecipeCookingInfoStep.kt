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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.CostInput
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.DifficultyInput
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.DurationInput
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.NextStepButton
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.PreviousStepButton
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.ServingsInput

@Composable
fun RecipeCookingVideoStep(
    goToPreviousStep: () -> Unit,
    goToNextStep: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(24.dp)
            .imePadding()
    ) {
        DifficultyInput(
            onOptionSelected = {}
        )
        Spacer(Modifier.height(40.dp))
        DurationInput(
            onValueChange = {}
        )
        Spacer(Modifier.height(40.dp))
        CostInput(
            onOptionSelected = {}
        )
        Spacer(Modifier.height(40.dp))
        ServingsInput(
            value = 5,
            onClick = {}
        )
        Spacer(Modifier.weight(1f))
        Row {
            PreviousStepButton(
                onClick = { goToPreviousStep() }
            )
            Spacer(Modifier.width(8.dp))
            NextStepButton(
                text = "Add ingredients",
                onClick = { goToNextStep() }
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeCookingVideoStepPreview() {
    AppTheme {
        RecipeCookingVideoStep(
            goToNextStep = {},
            goToPreviousStep = {}
        )
    }
}
