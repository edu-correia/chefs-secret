package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun TransparentTextField(
    label: String,
    summary: String,
    example: String,
    value: String,
    onChange: (String) -> Unit
) {
    Column {
        Column {
            Text(
                text = label,
                color = AppTheme.colorScheme.primary,
                style = AppTheme.typography.labelNormal,
                fontSize = 32.sp
            )
            Text(
                text = summary,
                color = AppTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                style = AppTheme.typography.body
            )
        }
        Spacer(Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = { value -> onChange(value) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 20.sp),

            colors = TextFieldDefaults.colors(
                // Label colors
                focusedLabelColor = AppTheme.colorScheme.onBackground,
                unfocusedLabelColor = AppTheme.colorScheme.primary,

                // Text colors
                focusedTextColor = AppTheme.colorScheme.onBackground,
                unfocusedTextColor = AppTheme.colorScheme.primary,

                // Container colors
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,

                // Extra colors
                cursorColor = AppTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = AppTheme.colorScheme.primary,
                focusedIndicatorColor = AppTheme.colorScheme.onBackground,
            )
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = "Example: $example",
            color = AppTheme.colorScheme.primary.copy(0.7f),
            style = AppTheme.typography.bodySmall,
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TransparentTextFieldPreview() {
    AppTheme {
        TransparentTextField(
            label = "Title",
            summary = "This name should reflect how unique and special your recipe is.",
            value = "123",
            onChange = {},
            example = "Mac & Cheese"
        )
    }
}