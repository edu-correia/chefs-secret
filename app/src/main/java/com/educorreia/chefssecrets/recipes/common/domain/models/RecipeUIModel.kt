package com.educorreia.chefssecrets.recipes.common.domain.models

import com.educorreia.chefssecrets.core.data.domain.models.Recipe

data class RecipeUIModel (
    val id: String,
    val title: String,
    val description: String,
    val videoUrl: String,
    val photoUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val duration: Int,
    val servings: Int,
    val cost: String,
    val difficulty: String,
    val tags: List<String>,
    val owner: UserSummaryUIModel?
) {
    companion object {
        fun fromDomain(recipe: Recipe): RecipeUIModel {
            return RecipeUIModel(
                id = recipe.id,
                title = recipe.title,
                description = recipe.description,
                videoUrl = recipe.videoUrl,
                photoUrl = recipe.photoUrl,
                createdAt = recipe.createdAt.toString(),
                updatedAt = recipe.updatedAt.toString(),
                ingredients = recipe.ingredients,
                instructions = recipe.instructions,
                duration = recipe.duration,
                servings = recipe.servings,
                cost = recipe.cost.name.lowercase(),
                difficulty = recipe.difficulty.name.lowercase(),
                tags = recipe.tags.map { it.name },
                owner = UserSummaryUIModel.fromDomain(recipe.owner)
            )
        }
    }
}