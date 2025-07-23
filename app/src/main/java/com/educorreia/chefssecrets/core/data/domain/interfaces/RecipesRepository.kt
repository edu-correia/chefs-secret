package com.educorreia.chefssecrets.core.data.domain.interfaces

import com.educorreia.chefssecrets.core.data.domain.models.Recipe
import com.educorreia.chefssecrets.core.data.domain.models.RecipeSummary

interface RecipesRepository {
    suspend fun getRecipes(): List<RecipeSummary>
    suspend fun getRecipeById(recipeId: String): Recipe
}