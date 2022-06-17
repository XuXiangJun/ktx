package com.github.xuxiangjun.ext.collection

import kotlin.random.Random

fun <E> Collection<E>.random(): E {
    val index = Random.nextInt(0, size)
    for ((cur, e) in this.withIndex()) {
        if (cur == index) {
            return e
        }
    }

    throw ArrayIndexOutOfBoundsException()
}

