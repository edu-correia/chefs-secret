package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem

data class RecipesListUiState(
    val recipeItemList: List<RecipeItem>
)
