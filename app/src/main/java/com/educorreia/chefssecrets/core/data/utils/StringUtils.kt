package com.educorreia.chefssecrets.core.data.utils

import java.util.Locale

fun capitalizeString(entry: String): String {
    return entry.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
        else it.toString()
    }
}