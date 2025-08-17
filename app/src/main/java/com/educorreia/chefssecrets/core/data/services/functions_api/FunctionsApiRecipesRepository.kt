package com.educorreia.chefssecrets.core.data.services.functions_api

import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.data.domain.models.Job
import com.educorreia.chefssecrets.core.data.domain.models.enums.JobType
import com.educorreia.chefssecrets.core.data.domain.models.Recipe
import com.educorreia.chefssecrets.core.data.domain.models.RecipeSummary
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.data.domain.models.VideoPreview
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeCost
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeDifficulty
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeTag
import com.educorreia.chefssecrets.core.data.services.functions_api.requests.RecipeExtractionRequest
import com.educorreia.chefssecrets.core.data.utils.convertStringToLocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FunctionsApiRecipesRepository(
    private val apiService: ApiService
) : RecipesRepository {
    override suspend fun getRecipes(): List<RecipeSummary> {
        return try {
            withContext(Dispatchers.IO) {
                val recipesList = apiService.getRecipes().body()!!.recipes

                recipesList.map {
                    RecipeSummary(
                        it.id,
                        it.title,
                        it.description,
                        it.photoUrl,
                        null
                    )
                }
            }
        } catch (e: Exception) {
            throw Error("Error:" + e.message, e)
        }
    }

    override suspend fun getRecipeById(recipeId: String): Recipe {
        return try {
            withContext(Dispatchers.IO) {
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
            }
        } catch (e: Exception) {
            throw Error("Error:" + e.message, e)
        }
    }

    override suspend fun getVideoPreview(videoUrl: String): VideoPreview {
        return try {
            withContext(Dispatchers.IO) {
                val preview = apiService.getVideoPreview(videoUrl).body()!!.preview

                VideoPreview(
                    preview.previewImageUrl,
                    preview.videoOwnerUsername
                )
            }
        } catch (e: Exception) {
            throw Error("Error:" + e.message, e)
        }
    }

    override suspend fun enqueueExtraction(videoUrl: String): Job {
        return try {
            withContext(Dispatchers.IO) {
                val body = RecipeExtractionRequest(videoUrl = videoUrl)

                val jobResponse = apiService.enqueueRecipeExtraction(body).body()!!.job

                Job(
                    jobResponse.id,
                    jobResponse.status,
                    JobType.valueOf(jobResponse.type.toUpperCase()),
                    jobResponse.userId,
                    convertStringToLocalDateTime(jobResponse.createdAt),
                )
            }
        } catch (e: Exception) {
            throw Error("Error:" + e.message, e)
        }
    }
}