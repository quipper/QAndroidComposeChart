package com.quipper.qandroidcomposechart.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.convertFormat(from: String, to: String): String {
    val fromFormat = DateTimeFormatter.ofPattern(from, Locale.getDefault())
    val toFormat = DateTimeFormatter.ofPattern(to, Locale.getDefault())
    if (this.isBlank()) return ""
    val localDateTime = LocalDate.parse(this, fromFormat)
    localDateTime?.let {
        return localDateTime.format(toFormat)
    }
    return ""
}
