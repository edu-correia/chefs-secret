package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.domain.use_cases

import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.domain.exceptions.ExtractRecipeValidationException.*

class ValidateRecipeVideoUrl {
    fun execute(videoUrl: String) {
        if (videoUrl.isBlank()) throw EmptyVideoUrlException

        if (!videoUrl.startsWith("http://") && !videoUrl.startsWith("https://"))
            throw InvalidLinkForVideoUrlException

        if (!videoUrl.contains("instagram.com"))
            throw VideoUrlMustBeFromInstagramException
    }
}