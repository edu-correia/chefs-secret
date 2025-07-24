package com.educorreia.chefssecrets.core.data.utils

import android.text.format.DateUtils
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun convertStringToLocalDateTime(dateString: String): LocalDateTime {
    val instant = Instant.parse(dateString)

    val localZoneId = ZoneId.systemDefault()

    val localDateTime: LocalDateTime = instant.atZone(localZoneId).toLocalDateTime()

    return localDateTime
}

fun LocalDateTime.toRelativeTimeSpanString(): String {
    val nowInMillis = System.currentTimeMillis()

    val timeInMillis = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    return DateUtils.getRelativeTimeSpanString(
        timeInMillis,
        nowInMillis,
        DateUtils.MINUTE_IN_MILLIS
    ).toString()
}