package com.github.xuxiangjun.javaext

fun Byte.toBin(): String {
    val sb = StringBuilder(8)
    val a = (toInt() and 0xff).toString(2)
    val supplement = 8 - a.length
    for (i in 0 .. supplement) {
        sb.append('0')
    }
    sb.append(a)
    return sb.toString()
}

fun Byte.toHex(): String {
    val s = (toInt() and 0xff).toString(16)
    return if (s.length < 2) {
        "0$s"
    } else {
        s
    }
}

fun Byte.getBit(index: Int): Int {
    if (index < 0 || index >= Byte.SIZE_BITS) {
        throw IndexOutOfBoundsException("Byte is ${Byte.SIZE_BITS} bits, $index is invalid")
    }
    return toInt() shr index and 1
}

operator fun Char.get(index: Int): Byte {
    if (index < 0 || index >= Char.SIZE_BYTES) {
        throw IndexOutOfBoundsException("Char is ${Char.SIZE_BYTES} bytes, $index is invalid")
    }
    return (this.code shr (8 * index) and 0xff).toByte()
}

fun Char.getBit(index: Int): Int {
    if (index < 0 || index >= Char.SIZE_BITS) {
        throw IndexOutOfBoundsException("Char is ${Char.SIZE_BITS} bits, $index is invalid")
    }

    return this.code shr index and 1
}

/**
 * Get little endian ByteArray
 */
fun Char.toByteArray(): ByteArray {
    val i = this.code
    return ByteArray(Char.SIZE_BYTES) { index ->
        (i shr (8 * index) and 0xff).toByte()
    }
}

operator fun Short.get(index: Int): Byte {
    if (index < 0 || index >= Short.SIZE_BYTES) {
        throw IndexOutOfBoundsException("Char is ${Short.SIZE_BYTES} bytes, $index is invalid")
    }
    return (toInt() shr (8 * index) and 0xff).toByte()
}

fun Short.getBit(index: Int): Int {
    if (index < 0 || index >= Short.SIZE_BITS) {
        throw IndexOutOfBoundsException("Char is ${Short.SIZE_BITS} bits, $index is invalid")
    }
    return toInt() shr index and 1
}

/**
 * Get little endian ByteArray
 */
fun Short.toByteArray(): ByteArray {
    val i = toInt()
    return ByteArray(Short.SIZE_BYTES) { index ->
        (i shr (8 * index) and 0xff).toByte()
    }
}

operator fun Int.get(index: Int): Byte {
    if (index < 0 || index >= Int.SIZE_BYTES) {
        throw IndexOutOfBoundsException("Int is ${Int.SIZE_BYTES} bytes, $index is invalid")
    }
    return (this shr (8 * index) and 0xff).toByte()
}

fun Int.getBit(index: Int): Int {
    if (index < 0 || index >= Int.SIZE_BITS) {
        throw IndexOutOfBoundsException("Int is ${Int.SIZE_BITS} bits, $index is invalid")
    }
    return this shr index and 1
}

/**
 * Get little endian ByteArray
 */
fun Int.toByteArray(): ByteArray {
    val i = this
    return ByteArray(Int.SIZE_BYTES) { index ->
        (i shr (8 * index) and 0xff).toByte()
    }
}

operator fun Long.get(index: Int): Byte {
    if (index < 0 || index >= Long.SIZE_BYTES) {
        throw IndexOutOfBoundsException("Long is ${Long.SIZE_BYTES} bytes, $index is invalid")
    }
    return (this shr (8 * index) and 0xff).toByte()
}

fun Long.getBit(index: Int): Int {
    if (index < 0 || index >= Long.SIZE_BITS) {
        throw IndexOutOfBoundsException("Long is ${Long.SIZE_BITS} bits, $index is invalid")
    }
    return (this shr index and 1L).toInt()
}

/**
 * Get little endian ByteArray
 */
fun Long.toByteArray(): ByteArray {
    val l = this
    return ByteArray(Long.SIZE_BYTES) { index ->
        (l shr (8 * index) and 0xff).toByte()
    }
}
