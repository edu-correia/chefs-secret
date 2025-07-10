package com.educorreia.chefssecrets.core.data.domain.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String,
    val phoneNumber: String?,
) {
    fun getFirstName(): String {
        return name.split(" ")[0]
    }
}