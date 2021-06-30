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

operator fun Char.get(index: Int): Byte {
    if (index < 0 || index > 1) {
        throw IndexOutOfBoundsException("Char is always 2 bytes, $index is invalid")
    }
    return (this.code shr (8 * index) and 0xff).toByte()
}

fun Char.toByteArray(littleEndian: Boolean = true): ByteArray {
    val i = this.code
    return if (littleEndian) {
        byteArrayOf(
            (i and 0xff).toByte(),
            (i shr 8 and 0xff).toByte()
        )
    } else {
        byteArrayOf(
            (i shr 8 and 0xff).toByte(),
            (i and 0xff).toByte()
        )
    }
}

operator fun Int.get(index: Int): Byte {
    if (index < 0 || index > 3) {
        throw IndexOutOfBoundsException("Int is always 4 bytes, $index is invalid")
    }
    return (this shr (8 * index) and 0xff).toByte()
}

fun Int.toByteArray(littleEndian: Boolean = true): ByteArray {
    return if (littleEndian) {
        byteArrayOf(
            (this and 0xff).toByte(),
            (this shr 8 and 0xff).toByte(),
            (this shr 16 and 0xff).toByte(),
            (this shr 24 and 0xff).toByte()
        )
    } else {
        byteArrayOf(
            (this shr 24 and 0xff).toByte(),
            (this shr 16 and 0xff).toByte(),
            (this shr 8 and 0xff).toByte(),
            (this and 0xff).toByte(),
        )
    }
}

operator fun Long.get(index: Int): Byte {
    if (index < 0 || index > 7) {
        throw IndexOutOfBoundsException("Long is always 8 bytes, $index is invalid")
    }
    return (this shr (8 * index) and 0xff).toByte()
}

fun Long.toByteArray(littleEndian: Boolean = true): ByteArray {
    return if (littleEndian) {
        byteArrayOf(
            (this and 0xff).toByte(),
            (this shr 8 and 0xff).toByte(),
            (this shr 16 and 0xff).toByte(),
            (this shr 24 and 0xff).toByte(),
            (this shr 32 and 0xff).toByte(),
            (this shr 40 and 0xff).toByte(),
            (this shr 48 and 0xff).toByte(),
            (this shr 56 and 0xff).toByte()
        )
    } else {
        byteArrayOf(
            (this shr 56 and 0xff).toByte(),
            (this shr 48 and 0xff).toByte(),
            (this shr 40 and 0xff).toByte(),
            (this shr 32 and 0xff).toByte(),
            (this shr 24 and 0xff).toByte(),
            (this shr 16 and 0xff).toByte(),
            (this shr 8 and 0xff).toByte(),
            (this and 0xff).toByte(),
        )
    }
}
