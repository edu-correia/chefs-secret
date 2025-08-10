package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

sealed interface EnqueueRecipeExtractionAction {
    object Submit: EnqueueRecipeExtractionAction
    object LoginWithGoogle: EnqueueRecipeExtractionAction
}
