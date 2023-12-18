package com.github.xuxiangjun.ext.time

object SecondX : TimeValue {
    override val Second = 1L
    override val Minute = 60L
    override val Hour = 3600L
    override val Day = 86400L
    override val Week = 604800L

    fun howManyMinutes(seconds: Long): Long {
        return seconds / Minute
    }

    fun howManyHours(seconds: Long): Long {
        return seconds / Hour
    }

    fun howManyDays(seconds: Long): Long {
        return seconds / Day
    }

    fun howManyWeeks(seconds: Long): Long {
        return seconds / Week
    }
}
