package com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun ConditionalSpacer(
    showSpacer: Boolean = false,
    height: Dp = 0.dp
) {
    AnimatedVisibility(
        visible = showSpacer,
        enter = expandIn(),
        exit = shrinkOut()
    ) {
        Spacer(Modifier.height(height))
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ConditionalSpacerPreview() {
    AppTheme {
        ConditionalSpacer(showSpacer = true)
    }
}