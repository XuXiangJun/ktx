package com.github.xuxiangjun.javaext

import java.io.ByteArrayOutputStream
import java.security.MessageDigest
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
 * Convert [1, 2, 3, 4] to 0x04030201 if little endian, 0x01020304 if big endian
 */
fun ByteArray.toInt(bigEndian: Boolean = false): Int {
    if (size > 4) {
        throw IllegalArgumentException("Too big data, length can't greater than 4")
    }
    var ret = 0
    for (i in 0 until size) {
        val index = if (bigEndian) size - 1 - i else i
        ret = ((this[index].toInt() and 0xff) shl (8 * i)) or ret
    }
    return ret
}

/**
 * Convert [1, 2, 3, 4, 5, 6, 7, 8] to 0x0807060504030201 if little endian, 0x0102030405060708 if big endian
 */
fun ByteArray.toLong(bigEndian: Boolean = false): Long {
    if (size > 8) {
        throw IllegalArgumentException("Too big data, length can't greater than 8")
    }
    var ret = 0L
    for (i in 0 until size) {
        val index = if (bigEndian) size - 1 - i else i
        ret = ((this[index].toLong() and 0xffL) shl (8 * i)) or ret
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

fun ByteArray.md5(): ByteArray {
    val md = MessageDigest.getInstance("md5")
    return md.digest(this)
}

fun ByteArray.sha1(): ByteArray {
    val md = MessageDigest.getInstance("sha-1")
    return md.digest(this)
}

fun ByteArray.sha256(): ByteArray {
    val md = MessageDigest.getInstance("sha-256")
    return md.digest(this)
}
