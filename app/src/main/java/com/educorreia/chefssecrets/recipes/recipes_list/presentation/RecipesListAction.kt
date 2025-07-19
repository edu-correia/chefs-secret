package com.educorreia.chefssecrets.recipes.recipes_list.presentation

sealed interface RecipesListAction {
    object GoToCreateRecipePage: RecipesListAction
    data class GoToRecipeDetailsPage(val id: String): RecipesListAction
}