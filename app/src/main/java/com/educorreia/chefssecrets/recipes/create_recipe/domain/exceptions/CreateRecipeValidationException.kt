package com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions

sealed class CreateRecipeValidationException : Exception() {
    object EmptyRecipeNameException: CreateRecipeValidationException();
    object EmptyRecipeDescriptionException: CreateRecipeValidationException();
    object EmptyRecipeImageUrlException: CreateRecipeValidationException();
    object InvalidLinkForImageUrlException: CreateRecipeValidationException();
}