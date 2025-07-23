package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class RecipeSummaryResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("photoUrl")
    val photoUrl: String
)
