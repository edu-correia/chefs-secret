package com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions

sealed class ValidationException : Exception() {
    object EmptyRecipeNameException: ValidationException();
    object EmptyRecipeDescriptionException: ValidationException();
    object EmptyRecipeImageUrlException: ValidationException();
    object InvalidLinkForImageUrlException: ValidationException();
}