package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class RecipesListResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("recipes")
    val recipes: List<Recipe>
)
