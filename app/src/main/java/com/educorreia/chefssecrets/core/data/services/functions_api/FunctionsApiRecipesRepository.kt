package com.educorreia.chefssecrets.core.data.services.functions_api

import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.data.domain.models.Recipe
import com.educorreia.chefssecrets.core.data.domain.models.RecipeSummary
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeCost
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeDifficulty
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeTag
import com.educorreia.chefssecrets.core.data.utils.convertStringToLocalDateTime

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

    override suspend fun getRecipeById(recipeId: String): Recipe {
        return try {
            val recipe = apiService.getRecipeById(recipeId).body()!!.recipe

            Recipe(
                recipe.id,
                recipe.title,
                recipe.description,
                recipe.videoUrl,
                recipe.photoUrl,
                convertStringToLocalDateTime(recipe.createdAt),
                convertStringToLocalDateTime(recipe.updatedAt),
                recipe.ingredients,
                recipe.instructions,
                recipe.utensils,
                recipe.duration,
                recipe.servings,
                RecipeCost.valueOf(recipe.cost.toUpperCase()),
                RecipeDifficulty.valueOf(recipe.difficulty.toUpperCase()),
                recipe.tags.map { RecipeTag.valueOf(it.toUpperCase()) },
                owner = User(
                    recipe.owner.id,
                    recipe.owner.email,
                    recipe.owner.name,
                    recipe.owner.photoUrl
                ),
            )
        } catch (e: Exception) {
            throw Error("Error:" + e.message, e)
        }
    }
}