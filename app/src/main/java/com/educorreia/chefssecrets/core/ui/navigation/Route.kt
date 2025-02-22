package com.educorreia.chefssecrets.core.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object RecipesListRoute: Route

    @Serializable
    object CreateRecipeRoute: Route

    @Serializable
    data class RecipeDetailsRoute(
        val recipeId: String
    ): Route
}