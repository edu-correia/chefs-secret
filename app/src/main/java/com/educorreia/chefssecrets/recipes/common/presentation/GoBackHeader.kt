package com.educorreia.chefssecrets.recipes.common.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
    text: String? = null
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .bottomBorder(1.dp, AppTheme.colorScheme.onBackground)
            .padding(horizontal = 24.dp, vertical = 24.dp),
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

        if (text != null) {
            Text(
                text = text,
                color = AppTheme.colorScheme.primary,
                fontSize = 18.sp
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
            text = "Create Recipe"
        )
    }
}