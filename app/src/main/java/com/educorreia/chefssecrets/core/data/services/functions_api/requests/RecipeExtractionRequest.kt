package com.educorreia.chefssecrets.core.data.services.functions_api.requests

import com.google.gson.annotations.SerializedName

data class RecipeExtractionRequest(
    @SerializedName("videoUrl")
    val videoUrl: String,
)
