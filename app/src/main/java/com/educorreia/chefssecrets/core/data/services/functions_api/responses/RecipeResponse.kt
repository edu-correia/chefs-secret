package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("photoUrl")
    val photoUrl: String,

    @SerializedName("videoUrl")
    val videoUrl: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("difficulty")
    val difficulty: String,

    @SerializedName("cost")
    val cost: String,

    @SerializedName("duration")
    val duration: Int,

    @SerializedName("servings")
    val servings: Int,

    @SerializedName("ingredients")
    val ingredients: List<String>,

    @SerializedName("instructions")
    val instructions: List<String>,

    @SerializedName("utensils")
    val utensils: List<String>,

    @SerializedName("tags")
    val tags: List<String>,

    @SerializedName("ownerId")
    val ownerId: String,

    @SerializedName("owner")
    val owner: UserResponse
)
