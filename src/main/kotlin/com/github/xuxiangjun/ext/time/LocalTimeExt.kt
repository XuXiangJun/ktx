package com.github.xuxiangjun.ext.time

import java.time.LocalTime

val LocalTime.millisecond: Int
    get() = nano / 1_000_000

val LocalTime.microsecond: Int
    get() = nano / 1000
