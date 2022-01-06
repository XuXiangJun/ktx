package com.github.xuxiangjun.ext.time

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