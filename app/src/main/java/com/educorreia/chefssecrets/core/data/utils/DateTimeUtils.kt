package com.educorreia.chefssecrets.core.data.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun convertStringToLocalDateTime(dateString: String): LocalDateTime {
    val instant = Instant.parse(dateString)

    val localZoneId = ZoneId.systemDefault()

    val localDateTime: LocalDateTime = instant.atZone(localZoneId).toLocalDateTime()

    return localDateTime
}