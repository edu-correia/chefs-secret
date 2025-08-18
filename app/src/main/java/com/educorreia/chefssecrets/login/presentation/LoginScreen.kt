package com.educorreia.chefssecrets.login.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.presentation.GoogleSignInButton
import org.koin.androidx.compose.koinViewModel

val ANIMATION_NUMBER_OF_ITERATIONS = 1

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>()
) {
    LoginScreen(
        onEvent = viewModel::onEvent
    )
}

@Composable
fun LoginScreen(
    onEvent: (LoginEvent) -> Unit
) {
    val gradientColors = listOf(AppTheme.colorScheme.secondary, AppTheme.colorScheme.secondary, AppTheme.colorScheme.background)

    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                )
            )
            .padding(horizontal = 32.dp, vertical = 64.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_app_name_drawing),
            contentDescription = "Chef's Secrets",
            tint = AppTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(Modifier.height(40.dp))

        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.recipes_book_animation),
        )
        LottieAnimation(
            composition = composition,
            iterations = ANIMATION_NUMBER_OF_ITERATIONS,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = "Watch. Share. Cook.",
            style = AppTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            color = AppTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text =
                "Tired of amazing recipe videos disappearing forever?\n" +
                "Don't let it get lost in the scroll.\n" +
                "Share the video with us, and we'll turn it\n" +
                "into a step-by-step recipe for your kitchen.",
            style = AppTheme.typography.body,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.primary,
        )

        Spacer(Modifier.weight(2f))

        GoogleSignInButton {
            onEvent(LoginEvent.LoginWithGoogle)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    AppTheme {
        LoginScreen(
            onEvent = {}
        )
    }
}