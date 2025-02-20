package com.educorreia.chefssecrets.core.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SystemBarColor(
    color: Color,
    isLightIcons: Boolean = isSystemInDarkTheme()
) {
    val isNotInPreviewMode = !LocalInspectionMode.current
    val view = LocalView.current
    if (isNotInPreviewMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !isLightIcons
        }
    }
}

