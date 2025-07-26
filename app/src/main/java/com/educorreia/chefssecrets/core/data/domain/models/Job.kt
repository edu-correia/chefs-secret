package com.educorreia.chefssecrets.core.data.domain.models

import java.time.LocalDateTime

data class Job(
    val id: String,
    val status: String,
    val type: JobType,
    val userId: String,
    val createdAt: LocalDateTime,
)
