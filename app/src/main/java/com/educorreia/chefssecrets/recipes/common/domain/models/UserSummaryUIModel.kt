package com.educorreia.chefssecrets.recipes.common.domain.models

import com.educorreia.chefssecrets.core.data.domain.models.User

data class UserSummaryUIModel (
    val id: String,
    val name: String,
    val photoUrl: String
) {
    companion object {
        fun fromDomain(user: User): UserSummaryUIModel {
            return UserSummaryUIModel(
                id = user.id,
                name = user.name,
                photoUrl = user.photoUrl
            )
        }
    }
}