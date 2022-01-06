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

/**
 * Get Entry by index
 */
fun <K, V> LinkedHashMap<K, V>.getByIndex(index: Int): Map.Entry<K, V> {
    if (index < 0 || index >= size) {
        throw IndexOutOfBoundsException("LinkedHashMap size is $size, index is $index")
    }

    var count = 0
    for (entry in this) {
        if (count == index) {
            return entry
        }
        ++count
    }

    throw IndexOutOfBoundsException("LinkedHashMap size is $size, index is $index")
}

fun <K, V> Map<K, V>.random(): Map.Entry<K, V> {
    val index = Random.nextInt(0, size)
    var cur = 0
    for (e in this) {
        if (cur == index) {
            return e
        }
        ++cur
    }

    throw ArrayIndexOutOfBoundsException()
}

fun <K, V> copy(src: Map<K, V>, to: MutableMap<K, V>, keys: Iterable<K>) {
    for (key in keys) {
        val v = src[key]
        if (v != null) {
            to[key] = v
        }
    }
}

fun <K, V> Map<K, V>.copyOf(vararg keys: K): Map<K, V> {
    val result = mutableMapOf<K, V>()
    copy(this, result, keys.toList())
    return result
}

fun <K, V> Map<K, V>.copyTo(to: MutableMap<K, V>, vararg keys: K) {
    copy(this, to, keys.toList())
}

fun <K, V> MutableMap<K, V>.copyFrom(from: Map<K, V>, vararg keys: K) {
    copy(from, this, keys.toList())
}

fun <SK, SV, DK, DV> Map<SK, SV>.mapMap(transform: (Map.Entry<SK, SV>) -> Pair<DK, DV>): Map<DK, DV> {
    val ret = mutableMapOf<DK, DV>()
    for (entry in this) {
        val pair = transform(entry)
        ret[pair.first] = pair.second
    }
    return ret
}
