package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeSummaryUIModel

data class RecipesListUiState(
    val isLoading: Boolean = false,
    val recipesList: List<RecipeSummaryUIModel> = listOf()
)
