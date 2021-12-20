package com.github.xuxiangjun.javaext

object MilliSecondX {
    const val Second = 1000L
    const val Minute = 60000L
    const val Hour = 3600000L
    const val Day = 86400000L
    const val Week = 604800000L

    fun seconds(many: Int): Long {
        return Second * many
    }

    fun minutes(many: Int): Long {
        return Minute * many
    }

    fun hours(many: Int): Long {
        return Hour * many
    }

    fun days(many: Int): Long {
        return Day * many
    }

    fun weeks(many: Int): Long {
        return Week * many
    }
}
