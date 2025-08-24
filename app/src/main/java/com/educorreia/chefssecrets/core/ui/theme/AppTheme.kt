package com.educorreia.chefssecrets.core.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val lightColorTheme = AppColorScheme(
    background = White,
    onBackground = Green90,
    primary = Green70,
    onPrimary = Color.Unspecified,
    secondary = Yellow20,
    onSecondary = Color.Unspecified,
    tertiary = Green30,
    onTertiary = White
)

private val darkColorTheme = AppColorScheme(
    background = Green90,
    onBackground = White,
    primary = Yellow20,
    onPrimary = Color.Unspecified,
    secondary = Green70,
    onSecondary = Color.Unspecified,
    tertiary = Green30,
    onTertiary = White,
)

private val typography = AppTypography(
    body = TextStyle(
        fontFamily = AgdasimaFont,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = AgdasimaFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    titleLarge = TextStyle(
        fontFamily = AclonicaFont,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    titleNormal = TextStyle(
        fontFamily = AclonicaFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = TextStyle(
        fontFamily = AgdasimaFont,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    labelNormal = TextStyle(
        fontFamily = AgdasimaFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    labelLarge = TextStyle.Default,
    labelSmall = TextStyle.Default
)

private val shape = AppShape(
    container = RoundedCornerShape(8.dp),
    button = RoundedCornerShape(8.dp),
)

private val size = AppSize(
    large = 24.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) darkColorTheme else lightColorTheme
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalIndication provides rippleIndication,
        content = content
    )
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current
}