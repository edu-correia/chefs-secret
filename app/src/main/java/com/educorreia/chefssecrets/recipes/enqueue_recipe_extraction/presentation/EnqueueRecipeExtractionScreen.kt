package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.ui.auth.UserState
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.domain.models.VideoPreviewUIModel
import com.educorreia.chefssecrets.recipes.common.presentation.FloatingRoundButton
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables.BackgroundImage
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables.ExtractRecipeButton
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables.RecipePreview
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables.ShimmerExtractRecipe
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables.ShimmerRecipePreview
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables.SignInBeforeExtraction
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EnqueueRecipeExtractionScreenRoot(
    videoUrl: String,
    onDismiss: () -> Unit
) {
    val viewModel: EnqueueRecipeExtractionViewModel = koinViewModel(
        parameters = { parametersOf(videoUrl) }
    )

    val uiState = viewModel.uiState.collectAsState()
    val userState = viewModel.userState.collectAsState()

    LaunchedEffect(true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is EnqueueRecipeExtractionEffect.CloseScreen -> {
                    onDismiss()
                }
            }
        }
    }

    EnqueueRecipeExtractionScreen(videoUrl, onDismiss, uiState.value,
        viewModel::onEvent, userState.value)
}

@Composable
fun EnqueueRecipeExtractionScreen(
    videoUrl: String,
    onDismiss: () -> Unit,
    uiState: EnqueueRecipeExtractionUiState,
    onEvent: (EnqueueRecipeExtractionAction) -> Unit,
    userState: UserState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage()

        FloatingRoundButton(
            icon = Icons.Default.Close,
            onClick = { onDismiss() },
            modifier = Modifier.align(Alignment.TopStart),
            contentDescription = "Close"
        )

        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            if (userState.isLoggedIn) {
                if (uiState.isLoading) {
                    ShimmerRecipePreview()
                    Spacer(Modifier.height(32.dp))
                    ShimmerExtractRecipe()
                } else {
                    RecipePreview(
                        videoUrl = videoUrl,
                        user = userState.currentUser,
                        videoPreview = uiState.videoPreview
                    )
                    Spacer(Modifier.height(32.dp))
                    ExtractRecipeButton {
                        onEvent(EnqueueRecipeExtractionAction.Submit)
                    }
                }
            } else {
                SignInBeforeExtraction {
                    onEvent(EnqueueRecipeExtractionAction.LoginWithGoogle)
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EnqueueRecipeExtractionScreenPreview() {
    AppTheme {
        EnqueueRecipeExtractionScreen(
            videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
            onDismiss = {},
            uiState = EnqueueRecipeExtractionUiState(
                isLoading = false,
                videoPreview = VideoPreviewUIModel(
                    "https://i.imgur.com/R0eBtWi.png",
                    "username"
                ),
            ),
            onEvent = {},
            userState = UserState(
                isLoggedIn = true,
                currentUser = User(
                    id = "1234",
                    name = "Jane Doe",
                    email = "test@mail.com",
                    photoUrl = "https://i.imgur.com/R0eBtWi.png",
                )
            )
        )
    }
}