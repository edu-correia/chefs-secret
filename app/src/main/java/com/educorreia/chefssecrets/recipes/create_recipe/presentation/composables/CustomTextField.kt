package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    placeholder: String? = null
) {
    Column (
        horizontalAlignment = Alignment.End
    ) {
        TextField(
            label = {
                Text(label)
            },
            value = value,
            onValueChange = { value -> onChange(value) },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(placeholder ?: "")
            },
            colors = TextFieldDefaults.colors(
                // Label colors
                focusedLabelColor = AppTheme.colorScheme.onBackground,
                unfocusedLabelColor = AppTheme.colorScheme.primary,

                // Text colors
                focusedTextColor = AppTheme.colorScheme.onBackground,
                unfocusedTextColor = AppTheme.colorScheme.onBackground,

                // Container colors
                unfocusedContainerColor = AppTheme.colorScheme.secondary,
                focusedContainerColor = AppTheme.colorScheme.secondary,

                // Extra colors
                cursorColor = AppTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = AppTheme.colorScheme.primary,
                focusedIndicatorColor = AppTheme.colorScheme.onBackground
            )
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    AppTheme {
        CustomTextField(
            label = "Recipe name",
            value = "123",
            onChange = {}
        )
    }
}