package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class VideoPreviewResponse(
    @SerializedName("previewImageUrl")
    val previewImageUrl: String,

    @SerializedName("videoOwnerUsername")
    val videoOwnerUsername: String,
)
