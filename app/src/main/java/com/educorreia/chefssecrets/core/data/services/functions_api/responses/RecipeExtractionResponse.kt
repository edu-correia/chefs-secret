package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class RecipeExtractionResponse(
    @SerializedName("id")
    val message: String,

    @SerializedName("recipe")
    val job: JobResponse
)