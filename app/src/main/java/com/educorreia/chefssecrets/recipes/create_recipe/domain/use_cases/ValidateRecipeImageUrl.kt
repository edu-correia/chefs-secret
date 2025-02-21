package com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases

import com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions.ValidationException.*

class ValidateRecipeImageUrl {
    fun execute(imageUrl: String) {
        if (imageUrl.isBlank()) throw EmptyRecipeImageUrlException

        if (!imageUrl.startsWith("http://") && !imageUrl.startsWith("https://"))
            throw InvalidLinkForImageUrlException
    }
}