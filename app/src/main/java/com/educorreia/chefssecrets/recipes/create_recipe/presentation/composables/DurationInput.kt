package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

val POSSIBLE_DURATION_VALUES = listOf(1, 2, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70,
    75, 80, 85, 90, 95, 100, 105, 110, 115, 120)
val DEFAULT_DURATION_VALUE = 30

@Composable
fun DurationInput(
    onValueChange: (Int) -> Unit
) {
    Column {
        Column {
            Text(
                text = "Duration (in minutes)",
                color = AppTheme.colorScheme.primary,
                style = AppTheme.typography.labelNormal,
                fontSize = 32.sp
            )
            Text(
                text = "The duration to prepare all the ingredients and cook the recipe.",
                color = AppTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                style = AppTheme.typography.body
            )
        }
        NumberCarouselPicker(
            values = POSSIBLE_DURATION_VALUES,
            currentValue = DEFAULT_DURATION_VALUE,
            onValueChange = { value -> onValueChange(value) }
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DurationInputPreview() {
    AppTheme {
        DurationInput(
            onValueChange = {}
        )
    }
}