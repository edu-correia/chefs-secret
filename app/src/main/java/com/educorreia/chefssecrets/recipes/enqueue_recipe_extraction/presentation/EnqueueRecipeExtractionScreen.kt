package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.theme.SystemBarColor

@Composable
fun EnqueueRecipeExtractionScreenRoot(
    videoLink: String,
    onDismiss: () -> Unit
    // viewModel: EnqueueRecipeExtractionViewModel = koinViewModel<EnqueueRecipeExtractionViewModel>()
) {
    EnqueueRecipeExtractionScreen(
        videoLink,
        onDismiss
    )
}

@Composable
fun EnqueueRecipeExtractionScreen(
    videoLink: String,
    onDismiss: () -> Unit
) {
    SystemBarColor(color = AppTheme.colorScheme.secondary)

    Column {
        Spacer(Modifier.height(20.dp))
        Text("Video Link Extracted: $videoLink")
        Spacer(Modifier.height(20.dp))
        Button(onClick = { onDismiss() }) {
            Text("Save")
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EnqueueRecipeExtractionScreenPreview() {
    AppTheme {
        EnqueueRecipeExtractionScreen(
            videoLink = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
            onDismiss = {}
        )
    }
}