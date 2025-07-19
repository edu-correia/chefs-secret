package com.educorreia.chefssecrets.recipes.common.domain.models

data class RecipeSummaryUIModel (
    val id: String,
    val title: String,
    val description: String,
    val photoUrl: String,
    val owner: UserSummaryUIModel?
)