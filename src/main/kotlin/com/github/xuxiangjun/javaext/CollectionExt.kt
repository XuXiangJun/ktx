package com.github.xuxiangjun.javaext

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