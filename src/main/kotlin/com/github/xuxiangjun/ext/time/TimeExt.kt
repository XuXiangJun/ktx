package com.github.xuxiangjun.ext.time

import java.sql.Timestamp
import java.time.*
import java.util.*

fun Date.toLocalDate(zone: ZoneId = ZoneId.systemDefault()): LocalDate {
    return toInstant().atZone(zone).toLocalDate()
}

fun Date.toLocalTime(zone: ZoneId = ZoneId.systemDefault()): LocalTime {
    return toInstant().atZone(zone).toLocalTime()
}

fun Date.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return toInstant().atZone(zone).toLocalDateTime()
}

val LocalDateTime.millisecond: Int
    get() = nano / 1_000_000

val LocalDateTime.microsecond: Int
    get() = nano / 1000

val LocalDateTime.timestamp: Long
    get() = Timestamp.valueOf(this).time

fun LocalDateTime.toDate(): Date {
    return Date.from(atZone(ZoneId.systemDefault()).toInstant())
}

val LocalTime.millisecond: Int
    get() = nano / 1_000_000

val LocalTime.microsecond: Int
    get() = nano / 1000

val YearMonth.firstDay: Int get() = 1

val YearMonth.lastDay: Int get() = lengthOfMonth()

fun systemZoneOffset(): ZoneOffset {
    return ZoneId.systemDefault().rules.getOffset(Instant.now())
}
