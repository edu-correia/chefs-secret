package com.educorreia.chefssecrets.recipes.common.domain.models

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
)