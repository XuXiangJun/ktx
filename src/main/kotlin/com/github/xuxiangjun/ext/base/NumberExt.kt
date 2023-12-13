package com.github.xuxiangjun.ext.base

import java.math.BigDecimal
import java.math.BigInteger
import java.text.DecimalFormat

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


//region size convert
fun Long.byteToKB(): Double {
    return toDouble() / 1024.0
}

fun Int.byteToKB(): Double {
    return toDouble() / 1024.0
}

fun Long.byteToMB(): Double {
    return toDouble() / 1048576.0 // 1048576 == 1024 * 1024
}

fun Int.byteToMB(): Double {
    return toDouble() / 1048576.0
}

fun Long.byteToGB(): Double {
    return toDouble() / 1073741824.0 // 1073741824 == 1024 * 1024 * 1024
}

fun Int.byteToGB(): Double {
    return toDouble() / 1073741824.0
}

fun Double.KBToBytes(): Long {
    return (this * 1024.0).toLong()
}

fun Float.KBToBytes(): Long {
    return (this * 1024f).toLong()
}

fun Long.KBToBytes(): Long {
    return this * 1024L
}

fun Int.KBToBytes(): Long {
    return this * 1024L
}

fun Double.KBToMB(): Double {
    return this / 1024.0
}

fun Float.KBToMB(): Double {
    return toDouble() / 1024.0
}

fun Long.KBToMB(): Double {
    return toDouble() / 1024.0
}

fun Int.KBToMB(): Double {
    return toDouble() / 1024.0
}

fun Double.KBToGB(): Double {
    return this / 1048576.0
}

fun Float.KBToGB(): Double {
    return toDouble() / 1048576.0
}

fun Long.KBToGB(): Double {
    return toDouble() / 1048576.0
}

fun Int.KBToGB(): Double {
    return toDouble() / 1048576.0
}

fun Double.MBToBytes(): Long {
    return (this * 1048576.0).toLong()
}

fun Float.MBToBytes(): Long {
    return (this * 1048576f).toLong()
}

fun Long.MBToBytes(): Long {
    return this * 1048576L
}

fun Int.MBtoBytes(): Long {
    return this * 1048576L
}

fun Double.MBToKB(): Double {
    return this * 1024.0
}

fun Float.MBToKB(): Double {
    return toDouble() * 1024.0
}

fun Long.MBToKB(): Double {
    return toDouble() * 1024.0
}

fun Int.MBToKB(): Double {
    return toDouble() * 1024.0
}

fun Double.MBToGB(): Double {
    return this / 1024.0
}

fun Float.MBToGB(): Double {
    return toDouble() / 1024.0
}

fun Long.MBToGB(): Double {
    return toDouble() / 1024.0
}

fun Int.MBToGB(): Double {
    return toDouble() / 1024.0
}

fun Double.GBToBytes(): Long {
    return (this * 1073741824.0).toLong()
}

fun Float.GBToBytes(): Long {
    return (toDouble() * 1073741824.0).toLong()
}

fun Long.GBToBytes(): Long {
    return this * 1073741824L
}

fun Int.GBToBytes(): Long {
    return this * 1073741824L
}

fun Double.GBToKB(): Double {
    return this * 1048576.0
}

fun Float.GBToKB(): Double {
    return toDouble() * 1048576.0
}

fun Long.GBToKB(): Double {
    return toDouble() * 1048576.0
}

fun Int.GBToKB(): Double {
    return toDouble() * 1048576.0
}

fun Double.GBToMB(): Double {
    return this * 1024.0
}

fun Float.GBToMB(): Double {
    return toDouble() * 1024.0
}

fun Long.GBToMB(): Double {
    return toDouble() * 1024.0
}

fun Int.GBToMB(): Double {
    return toDouble() * 1024.0
}
//endregion

fun Int.toThousandthString(): String {
    return String.format("%,d", this)
}

fun Long.toThousandthString(): String {
    return String.format("%,d", this)
}

fun BigInteger.toThousandthString(): String {
    return DecimalFormat("#,###").format(this)
}

fun BigDecimal.toThousandthString(): String {
    val formatBuilder = StringBuilder("#,###")
    if (scale() > 0) {
        formatBuilder.append(".")
        formatBuilder.append("0".repeat(scale()))
    }
    val decimalFormat = DecimalFormat(formatBuilder.toString())
    return decimalFormat.format(this)
}
