package com.github.xuxiangjun.ext.random

import kotlin.random.Random

fun randomInteger(length: Int): String {
    val builder = StringBuilder()
    for (i in 0 until length) {
        builder.append(Random.nextInt(10))
    }
    return builder.toString()
}

fun randomString(length: Int): String {
    val builder = StringBuilder()
    for (i in 0 until length) {
        val r = Random.nextInt(52)
        val base = if (r < 26) 'a'.code else 'A'.code - 26
        builder.append((base + r).toChar())
    }
    return builder.toString()
}
