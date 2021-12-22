package com.github.xuxiangjun.javaext

interface TimeX {
    val Second: Long
    val Minute: Long
    val Hour: Long
    val Day: Long
    val Week: Long

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

object MilliSecondX : TimeX {
    override val Second = 1000L
    override val Minute = 60000L
    override val Hour = 3600000L
    override val Day = 86400000L
    override val Week = 604800000L
}

object SecondX : TimeX {
    override val Second = 1L
    override val Minute = 60L
    override val Hour = 3600L
    override val Day = 86400L
    override val Week = 604800L
}
