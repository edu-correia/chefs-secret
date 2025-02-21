package com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases

import com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions.ValidationException

class ValidateRecipeDescription {
    fun execute(description: String) {
        if (description.isBlank()) throw ValidationException.EmptyRecipeDescriptionException
    }
}