package com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun SmallRecipeInfo(
    label: String,
    @DrawableRes iconDrawable: Int,
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(iconDrawable),
            modifier = Modifier.size(28.dp),
            tint = AppTheme.colorScheme.primary,
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.primary
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SmallRecipeInfoPreview() {
    AppTheme {
        SmallRecipeInfo(
            label = "30 min",
            iconDrawable = R.drawable.ic_clock
        )
    }
}