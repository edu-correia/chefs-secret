package com.educorreia.chefssecrets.recipes.common.domain.interfaces

import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeUIModel

interface RecipesRepository {
    fun getRecipes(): List<RecipeUIModel>
}