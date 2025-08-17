package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.domain.exceptions

sealed class ExtractRecipeValidationException : Exception() {
    object EmptyVideoUrlException: ExtractRecipeValidationException();
    object InvalidLinkForVideoUrlException: ExtractRecipeValidationException();
    object VideoUrlMustBeFromInstagramException: ExtractRecipeValidationException();
}