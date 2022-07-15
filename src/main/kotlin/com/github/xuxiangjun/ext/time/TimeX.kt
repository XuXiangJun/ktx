package com.github.xuxiangjun.ext.time

interface TimeX {
    val Second: Long
    val Minute: Long
    val Hour: Long
    val Day: Long
    val Week: Long

    fun seconds(seconds: Int): Long {
        return Second * seconds
    }

    fun minutes(minutes: Int): Long {
        return Minute * minutes
    }

    fun hours(hours: Int): Long {
        return Hour * hours
    }

    fun days(days: Int): Long {
        return Day * days
    }

    fun weeks(weeks: Int): Long {
        return Week * weeks
    }
}