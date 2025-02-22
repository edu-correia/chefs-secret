package com.educorreia.chefssecrets.recipes.recipes_list.presentation

sealed class RecipesListEvent {
    object GoToCreateRecipePage: RecipesListEvent()
}