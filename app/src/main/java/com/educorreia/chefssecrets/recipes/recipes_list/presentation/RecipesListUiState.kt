package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem

data class RecipesListUiState(
    val isLoading: Boolean = false,
    val recipesList: List<RecipeItem> = listOf()
)
