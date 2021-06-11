package com.github.xuxiangjun.javaext

import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Convert ByteArray to hex String
 */
fun ByteArray.toHex(upperCase: Boolean = false): String {
    val sb = StringBuilder(size * 2)
    for (b in this) {
        val i = b.toInt() and 0xff
        if (i < 0x10) {
            sb.append('0')
        }
        var hex = i.toString(16)
        if (upperCase) {
           hex = hex.uppercase()
        }
        sb.append(hex)
    }
    return sb.toString()
}

fun ByteArray.base64Encode(): ByteArray {
    return Base64.getEncoder().encode(this)
}

fun ByteArray.base64EncodeToString(): String {
    return Base64.getEncoder().encodeToString(this)
}

fun ByteArray.base64Decode(): ByteArray {
    return Base64.getDecoder().decode(this)
}

fun ByteArray.base64DecodeToString(): String {
    return Base64.getDecoder().decode(this).toString(Charsets.UTF_8)
}

/**
 * Convert [1, 2, 3, 4] to 0x04030201
 */
fun ByteArray.toInt(): Int {
    var ret = 0
    for (i in 0 until minOf(4, size)) {
        ret = ((this[i].toInt() and 0xff) shl (8 * i)) and ret
    }
    return ret
}

/**
 * Convert [1, 2, 3, 4, 5, 6, 7, 8] to 0x0807060504030201
 */
fun ByteArray.toLong(): Long {
    var ret = 0L
    for (i in 0 until minOf(8, size)) {
        ret = ((this[i].toLong() and 0xffL) shl (8 * i)) and ret
    }
    return ret
}

fun mergeBytes(vararg dataArray: ByteArray): ByteArray {
    return mergeBytesList(dataArray.toList())
}

fun mergeBytesArray(dataArray: Array<out ByteArray>): ByteArray {
    return mergeBytesList(dataArray.toList())
}

fun mergeBytesList(dataList: List<ByteArray>): ByteArray {
    val length = dataList.sumOf { it.size }
    val output = ByteArrayOutputStream(length)
    for (data in dataList) {
        output.write(data)
    }
    return output.toByteArray()
}
