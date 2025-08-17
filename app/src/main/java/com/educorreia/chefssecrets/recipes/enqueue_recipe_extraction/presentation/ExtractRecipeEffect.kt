package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

import com.educorreia.chefssecrets.core.ui.utils.UiText

sealed interface ExtractRecipeEffect {
    object CloseScreen: ExtractRecipeEffect
    data class ShowErrorMessage(val message: UiText) : ExtractRecipeEffect
}