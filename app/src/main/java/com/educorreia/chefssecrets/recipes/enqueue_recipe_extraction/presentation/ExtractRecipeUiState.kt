package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

import com.educorreia.chefssecrets.recipes.common.domain.models.VideoPreviewUIModel

data class ExtractRecipeUiState(
    val isLoading: Boolean = false,
    val videoPreview: VideoPreviewUIModel? = null,
)
