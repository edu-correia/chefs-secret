package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun ServingsInput(
    value: Int,
    onClick: () -> Unit,
) {
    Column {
        Column {
            Text(
                text = "Servings",
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
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    onClick()
                },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(AppTheme.colorScheme.primary, CircleShape)
                    .width(32.dp)
                    .height(32.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_minus),
                    tint = AppTheme.colorScheme.background,
                    contentDescription = "Subtract",
                    modifier = Modifier.width(20.dp).height(20.dp)
                )
            }

            Text(
                text = "$value",
                color = AppTheme.colorScheme.primary,
                fontSize = 28.sp
            )

            IconButton(
                onClick = {
                    onClick()
                },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(AppTheme.colorScheme.primary, CircleShape)
                    .width(32.dp)
                    .height(32.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    tint = AppTheme.colorScheme.background,
                    contentDescription = "Add",
                    modifier = Modifier.width(20.dp).height(20.dp)
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ServingsInputPreview() {
    AppTheme {
        ServingsInput(
            value = 5,
            onClick = {}
        )
    }
}