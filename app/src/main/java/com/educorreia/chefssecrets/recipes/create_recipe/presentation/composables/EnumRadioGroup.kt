package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun EnumRadioGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Loop through all the values of the enum
        options.forEach { option ->
            val isSelected = (option == selectedOption)

            // Define colors based on selection state
            val buttonColors = if (isSelected) {
                // Selected state colors
                ButtonDefaults.outlinedButtonColors(
                    containerColor = AppTheme.colorScheme.primary,
                    contentColor = AppTheme.colorScheme.background
                )
            } else {
                // Unselected state colors
                ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = AppTheme.colorScheme.primary.copy(alpha = 0.7f)
                )
            }

            OutlinedButton(
                onClick = { onOptionSelected(option) },
                colors = buttonColors,
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, AppTheme.colorScheme.primary),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = option)
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EnumRadioGroupPreview() {
    AppTheme {
        EnumRadioGroup(
            options = listOf("Easy", "Medium", "Hard"),
            selectedOption = "Easy",
            onOptionSelected = {}
        )
    }
}