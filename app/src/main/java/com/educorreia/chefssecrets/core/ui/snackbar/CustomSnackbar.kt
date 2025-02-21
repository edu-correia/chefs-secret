package com.educorreia.chefssecrets.core.ui.snackbar

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun CustomSnackbar(visuals: CustomSnackbarVisuals) {
    Snackbar(
        containerColor = visuals.containerColor,
        modifier = Modifier
            .padding(24.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (visuals.icon != null) {
                Icon(
                    imageVector = visuals.icon,
                    contentDescription = visuals.message,
                    tint = visuals.iconColor
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            Text(
                text = visuals.message,
                color = visuals.textColor
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    AppTheme {
        CustomSnackbar(
            CustomSnackbarVisuals(
                message = "Snackbar message",
                icon = Icons.Default.Done,
                iconColor = AppTheme.colorScheme.secondary,
                textColor = AppTheme.colorScheme.secondary,
                containerColor = AppTheme.colorScheme.onBackground
            )
        )
    }
}