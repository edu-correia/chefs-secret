package com.educorreia.chefssecrets.core.data.domain.interfaces

import com.educorreia.chefssecrets.core.data.domain.models.Job
import com.educorreia.chefssecrets.core.data.domain.models.Recipe
import com.educorreia.chefssecrets.core.data.domain.models.RecipeSummary
import com.educorreia.chefssecrets.core.data.domain.models.VideoPreview

interface RecipesRepository {
    suspend fun getRecipes(): List<RecipeSummary>
    suspend fun getRecipeById(recipeId: String): Recipe
    suspend fun getVideoPreview(videoUrl: String): VideoPreview
    suspend fun enqueueExtraction(videoUrl: String): Job
}