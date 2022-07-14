package com.github.xuxiangjun.ext.time

import java.sql.Timestamp
import java.time.LocalDateTime

val LocalDateTime.millisecond: Int
    get() = nano / 1_000_000

val LocalDateTime.microsecond: Int
    get() = nano / 1000

val LocalDateTime.timestamp: Long
    get() = Timestamp.valueOf(this).time