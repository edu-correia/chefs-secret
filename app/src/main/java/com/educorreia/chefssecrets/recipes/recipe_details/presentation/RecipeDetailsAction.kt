package com.educorreia.chefssecrets.recipes.recipe_details.presentation

sealed interface RecipeDetailsAction {
    object GoBack: RecipeDetailsAction
}