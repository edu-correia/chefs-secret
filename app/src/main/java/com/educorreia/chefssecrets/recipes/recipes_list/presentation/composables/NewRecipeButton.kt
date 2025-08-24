package com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.theme.AclonicaFont
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun NewRecipeButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(bottom = 24.dp)
            .width(280.dp)
            .height(64.dp),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = AppTheme.colorScheme.tertiary
        ),
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_cook_hat),
            contentDescription = null
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = "New recipe",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = AclonicaFont
            ),
            color = AppTheme.colorScheme.onTertiary
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NewRecipeButtonPreview() {
    AppTheme {
        NewRecipeButton (
            onClick = {}
        )
    }
}
