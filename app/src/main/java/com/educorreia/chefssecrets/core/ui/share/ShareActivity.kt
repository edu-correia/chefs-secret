package com.educorreia.chefssecrets.core.ui.share

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.EnqueueRecipeExtractionScreenRoot

class ShareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shareData: String? = when (intent?.action) {
            Intent.ACTION_SEND -> {
                intent.getStringExtra(Intent.EXTRA_TEXT)
            }
            else -> null
        }

        setContent {
            AppTheme {
                if (shareData != null) {
                    EnqueueRecipeExtractionScreenRoot(
                        videoUrl = shareData,
                        onDismiss = { finish() }
                    )
                } else {
                    finish()
                }
            }
        }
    }
}