package com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases

import com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions.ValidationException

class ValidateRecipeName {
    fun execute(name: String) {
        if (name.isBlank()) throw ValidationException.EmptyRecipeNameException
    }
}