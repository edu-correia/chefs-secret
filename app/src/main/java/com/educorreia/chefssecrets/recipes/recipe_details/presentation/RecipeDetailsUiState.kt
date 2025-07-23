package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeUIModel

data class RecipeDetailsUiState (
    val isLoading: Boolean = false,
    val recipe: RecipeUIModel? = null
)