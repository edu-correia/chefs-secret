package com.educorreia.chefssecrets.core.data.services.functions_api.responses

import com.google.gson.annotations.SerializedName

data class FetchPreviewResponse (
    @SerializedName("message")
    val message: String,

    @SerializedName("preview")
    val preview: VideoPreviewResponse
)