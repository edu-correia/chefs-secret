package com.educorreia.chefssecrets.recipes.create_recipe.presentation

sealed class CreateRecipeEvent {
    data class NameChanged(val name: String) : CreateRecipeEvent()
    data class DescriptionChanged(val description: String) : CreateRecipeEvent()
    data class ImageUrlChanged(val imageUrl: String) : CreateRecipeEvent()
    object Submit: CreateRecipeEvent()
    object GoBack: CreateRecipeEvent()
}