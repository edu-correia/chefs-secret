package com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun SubtopicTitle(
    title: String,
    @DrawableRes iconDrawableRes: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconDrawableRes),
            modifier = Modifier.size(28.dp),
            tint = AppTheme.colorScheme.primary,
            contentDescription = null
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = title,
            style = AppTheme.typography.titleSmall,
            color = AppTheme.colorScheme.primary
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SubtopicTitlePreview() {
    AppTheme {
        SubtopicTitle(
            title = "Ingredients",
            iconDrawableRes = R.drawable.ic_carrot
        )
    }
}