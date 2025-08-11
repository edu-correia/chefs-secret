package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

sealed interface ExtractRecipeAction {
    object Submit: ExtractRecipeAction
    object LoginWithGoogle: ExtractRecipeAction
}
