package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailsRoute(
    val recipeId: String
)
