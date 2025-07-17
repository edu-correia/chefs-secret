package com.educorreia.chefssecrets.core.data.services.functions_api

import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.data.domain.models.Recipe

class FunctionsApiRecipesRepository(
    private val apiService: ApiService
) : RecipesRepository {
    override suspend fun getRecipes(): List<Recipe> {
        return try {
            apiService.getRecipes().body()!!.recipes.map {
                Recipe(
                    it.id,
                    it.title,
                    it.description,
                    it.photoUrl
                )
            }
        } catch (e: Exception) {
            throw Error("Error:" + e.message, e)
        }
    }
}