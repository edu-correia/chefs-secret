package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class SpecificRecipeResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("recipe")
    val recipe: RecipeResponse
)
