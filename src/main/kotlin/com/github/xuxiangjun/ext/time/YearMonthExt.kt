package com.github.xuxiangjun.ext.time

import java.time.YearMonth

val YearMonth.lastDay: Int
    get() {
        for (day in 31 downTo 1) {
            if (isValidDay(day)) {
                return day
            }
        }

        throw IllegalArgumentException("Can't be here, get lastDay error")
    }
