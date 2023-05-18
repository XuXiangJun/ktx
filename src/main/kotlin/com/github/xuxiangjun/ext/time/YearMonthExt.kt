package com.github.xuxiangjun.ext.time

import java.time.YearMonth

val YearMonth.firstDay: Int get() = 1

val YearMonth.lastDay: Int get() = lengthOfMonth()
