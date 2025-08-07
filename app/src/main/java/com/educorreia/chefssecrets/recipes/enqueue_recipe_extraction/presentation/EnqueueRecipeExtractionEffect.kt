package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

sealed interface EnqueueRecipeExtractionEffect {
    object CloseScreen: EnqueueRecipeExtractionEffect
}