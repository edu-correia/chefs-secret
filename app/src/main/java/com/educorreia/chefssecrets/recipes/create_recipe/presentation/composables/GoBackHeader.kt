package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.custom_modifiers.bottomBorder
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.utils.UiText

@Composable
fun GoBackHeader(
    onGoBack: () -> Unit,
    mainText: String,
    description: String
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .clickable(onClick = onGoBack),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = UiText.StringResource(R.string.go_back).asString(),
            tint = AppTheme.colorScheme.primary,
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = mainText,
                color = AppTheme.colorScheme.primary,
                style = AppTheme.typography.titleNormal,
                fontSize = 22.sp
            )
            Text(
                text = description,
                color = AppTheme.colorScheme.onBackground,
                style = AppTheme.typography.body,
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GoBackHeaderPreview() {
    AppTheme {
        GoBackHeader(
            onGoBack = {},
            mainText = "Create Recipe",
            description = "There you are! Let’s create a fun and intuitive title and description for your recipe!"
        )
    }
}