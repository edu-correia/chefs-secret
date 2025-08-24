package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

enum class CookDifficulty {
    EASY,
    MEDIUM,
    HARD
}

@Composable
fun DifficultyInput(
    onOptionSelected: (String) -> Unit
) {
    Column {
        Column {
            Text(
                text = "Difficulty",
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
        Spacer(Modifier.height(16.dp))
        EnumRadioGroup(
            options = CookDifficulty.entries.map { it.name },
            selectedOption = CookDifficulty.MEDIUM.name,
            onOptionSelected = { value -> onOptionSelected(value) }
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DifficultyInputPreview() {
    AppTheme {
        DifficultyInput(
            onOptionSelected = {}
        )
    }
}