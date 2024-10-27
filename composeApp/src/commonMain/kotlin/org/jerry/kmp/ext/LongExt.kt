package org.jerry.kmp.ext

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.padZero(): String = toString().padStart(2, '0')

fun Long.convertTimeFormat(): String {
    val seconds = this / 1000
    val minutes = seconds / 60
    val hours = minutes / 60

    val remainingSeconds = seconds % 60
    val remainingMinutes = minutes % 60

    return when {
        hours > 0 -> "${hours.padZero()}:${remainingMinutes.padZero()}:${remainingSeconds.padZero()}"
        else -> "${remainingMinutes.padZero()}:${remainingSeconds.padZero()}"
    }
}

//ref: https://android.googlesource.com/platform/frameworks/base/+/0e2d281/core/java/android/text/format/DateUtils.java {function : formatElapsedTime}
fun Long.toDuration(): String {
    var hours: Long = 0
    var minutes: Long = 0
    var seconds: Long = this
    if (this >= 3600) {
        hours = this / 3600
        seconds -= hours * 3600
    }
    if (seconds >= 60) {
        minutes = seconds / 60
        seconds -= minutes * 60
    }

    return when {
        hours > 0 -> "${hours}:${minutes.padZero()}:${seconds.padZero()}"
        else -> "${minutes.padZero()}:${seconds.padZero()}"
    }
}

fun Long.toDateString(): String {
    if (this == 0L){
        return "---"
    }
    return try {
        val instant = Instant.fromEpochSeconds(this)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        with(dateTime) {
            "${year}-${monthNumber.toLong().padZero()}-${dayOfMonth.toLong().padZero()} " +
                    "${hour.toLong().padZero()}:${minute.toLong().padZero()}:${second.toLong().padZero()}"
        }
    } catch (e: Exception) {
        "---"
    }
}
