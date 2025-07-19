package com.educorreia.chefssecrets.core.data.services.functions_api

import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.data.domain.models.RecipeSummary

class FunctionsApiRecipesRepository(
    private val apiService: ApiService
) : RecipesRepository {
    override suspend fun getRecipes(): List<RecipeSummary> {
        return try {
            apiService.getRecipes().body()!!.recipes.map {
                RecipeSummary(
                    it.id,
                    it.title,
                    it.description,
                    it.photoUrl,
                    null
                )
            }
        } catch (e: Exception) {
            throw Error("Error:" + e.message, e)
        }
    }
}