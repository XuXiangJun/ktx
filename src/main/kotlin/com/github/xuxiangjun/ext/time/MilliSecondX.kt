package com.github.xuxiangjun.ext.time

object MilliSecondX : TimeValue {
    override val Second = 1000L
    override val Minute = 60000L
    override val Hour = 3600000L
    override val Day = 86400000L
    override val Week = 604800000L

    fun howManySeconds(milliseconds: Long): Long {
       return milliseconds / Second
    }

    fun howManyMinutes(milliseconds: Long): Long {
        return milliseconds / Minute
    }

    fun howManyHours(milliseconds: Long): Long {
        return milliseconds / Hour
    }

    fun howManyDays(milliseconds: Long): Long {
        return milliseconds / Day
    }

    fun howManyWeeks(milliseconds: Long): Long {
        return milliseconds / Week
    }
}
