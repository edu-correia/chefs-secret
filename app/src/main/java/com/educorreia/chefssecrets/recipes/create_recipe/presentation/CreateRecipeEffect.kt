package com.educorreia.chefssecrets.recipes.create_recipe.presentation

import com.educorreia.chefssecrets.core.ui.utils.UiText

sealed interface CreateRecipeEffect {
    data class ShowSnackbar(val message: UiText) : CreateRecipeEffect
}