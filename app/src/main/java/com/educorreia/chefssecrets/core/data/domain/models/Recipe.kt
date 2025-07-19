package com.educorreia.chefssecrets.core.data.domain.models

import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeCost
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeDifficulty
import com.educorreia.chefssecrets.core.data.domain.models.enums.RecipeTag
import java.time.LocalDateTime

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val videoUrl: String,
    val photoUrl: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val ingredients: List<String>,
    val instructions: List<String>,
    val duration: Int,
    val servings: Int,
    val cost: RecipeCost,
    val difficulty: RecipeDifficulty,
    val tags: List<RecipeTag>,
    val owner: User
)
