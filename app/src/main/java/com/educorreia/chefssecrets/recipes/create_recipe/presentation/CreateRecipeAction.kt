package com.educorreia.chefssecrets.recipes.create_recipe.presentation

sealed interface CreateRecipeAction {
    data class NameChanged(val name: String) : CreateRecipeAction
    data class DescriptionChanged(val description: String) : CreateRecipeAction
    data class ImageUrlChanged(val imageUrl: String) : CreateRecipeAction
    object Submit: CreateRecipeAction
    object GoBack: CreateRecipeAction
}