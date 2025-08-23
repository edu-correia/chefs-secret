package com.educorreia.chefssecrets.recipes.create_recipe.presentation

data class CreateRecipeUiState(
    val currentPage: FormSteps = FormSteps.TITLE_AND_DESC,
    val name: String = "",
    val description: String = "",
    val image: String = "",
)
