package com.educorreia.chefssecrets.core.data.domain.interfaces

import com.educorreia.chefssecrets.core.data.domain.models.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
}