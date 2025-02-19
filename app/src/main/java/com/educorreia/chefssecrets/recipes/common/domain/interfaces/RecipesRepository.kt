package com.educorreia.chefssecrets.recipes.common.domain.interfaces

import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem

interface RecipesRepository {
    fun getRecipes(): List<RecipeItem>
}