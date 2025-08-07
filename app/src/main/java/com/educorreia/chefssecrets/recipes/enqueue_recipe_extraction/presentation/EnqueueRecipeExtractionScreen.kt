package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.scaffold.PreviewScaffold
import com.educorreia.chefssecrets.core.ui.scaffold.ScaffoldSetup
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.domain.models.VideoPreviewUIModel
import com.educorreia.chefssecrets.recipes.common.presentation.GoBackHeader
import com.educorreia.chefssecrets.recipes.common.presentation.FilledButton
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

    LaunchedEffect(true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is EnqueueRecipeExtractionEffect.CloseScreen -> {
                    onDismiss()
                }
            }
        }
    }

//    ScaffoldSetup(
//        topBar = {
//            GoBackHeader(
//                onGoBack = {
//                    onDismiss()
//                },
//                text = "Extract recipe"
//            )
//        }
//    )

    EnqueueRecipeExtractionScreen(videoUrl, uiState.value, viewModel::onEvent)
}

@Composable
fun EnqueueRecipeExtractionScreen(
    videoUrl: String,
    uiState: EnqueueRecipeExtractionUiState,
    onEvent: (EnqueueRecipeExtractionAction) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recipe preview",
            style = AppTheme.typography.titleLarge,
            color = AppTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.size(24.dp))

        SubcomposeAsyncImage(
            model = uiState.videoPreview?.previewImageUrl ?: "",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(240.dp)
                .aspectRatio(12 / 16f)
                .clip(RoundedCornerShape(4.dp)),
            error = {
                Image(
                    painter = painterResource(R.drawable.img_recipe_example),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(240.dp)
                        .aspectRatio(12 / 16f)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Video link:",
                style = AppTheme.typography.titleSmall,
                color = AppTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = videoUrl,
                style = AppTheme.typography.body,
                color = AppTheme.colorScheme.onBackground,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Author username:",
                style = AppTheme.typography.titleSmall,
                color = AppTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "@${uiState.videoPreview?.videoOwnerUsername}",
                style = AppTheme.typography.body,
                color = AppTheme.colorScheme.onBackground,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        FilledButton(
            text = "Start extraction",
            onClick = {
            },
            modifier = Modifier
                .padding(bottom = 24.dp)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EnqueueRecipeExtractionScreenPreview() {
    AppTheme {
        PreviewScaffold(
            topBar = {
                GoBackHeader(
                    onGoBack = {},
                    text = "Extract recipe"
                )
            }
        ) {
            EnqueueRecipeExtractionScreen(
                videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                uiState = EnqueueRecipeExtractionUiState(
                    false,
                    VideoPreviewUIModel(
                        "https://i.imgur.com/R0eBtWi.png",
                        "username"
                    ),
                ),
                onEvent = {}
            )
        }
    }
}