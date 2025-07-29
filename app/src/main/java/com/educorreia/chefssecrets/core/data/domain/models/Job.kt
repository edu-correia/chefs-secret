package com.educorreia.chefssecrets.core.data.domain.models

import com.educorreia.chefssecrets.core.data.domain.models.enums.JobType
import java.time.LocalDateTime

data class Job(
    val id: String,
    val status: String,
    val type: JobType,
    val userId: String,
    val createdAt: LocalDateTime,
)
