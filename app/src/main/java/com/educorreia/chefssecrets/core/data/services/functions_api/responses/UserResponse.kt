package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("photoUrl")
    val photoUrl: String
)
