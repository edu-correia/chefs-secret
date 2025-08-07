package com.educorreia.chefssecrets.recipes.common.domain.models

import com.educorreia.chefssecrets.core.data.domain.models.VideoPreview

data class VideoPreviewUIModel(
    val previewImageUrl: String,
    val videoOwnerUsername: String
) {
    companion object {
        fun fromDomain(videoPreview: VideoPreview): VideoPreviewUIModel {
            return VideoPreviewUIModel(
                previewImageUrl = videoPreview.previewImageUrl,
                videoOwnerUsername = videoPreview.videoOwnerUsername
            )
        }
    }
}
