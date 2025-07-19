package com.educorreia.chefssecrets.core.data.domain.models

data class RecipeSummary(
    val id: String,
    val title: String,
    val description: String,
    val photoUrl: String,
    val owner: User?
)
