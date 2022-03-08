package com.github.xuxiangjun.ext.base

import com.github.xuxiangjun.ext.security.Hash
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*

/**
 * Convert hex String to ByteArray
 */
fun CharSequence.hexToByteArray(): ByteArray {
    if (isEmpty()) return ByteArray(0)

    val text = if (length and 1 == 0) this else "0$this"
    return ByteArray(text.length / 2) {
        val sub = text.substring(it * 2, it * 2 + 2)
        sub.toInt(16).toByte()
    }
}

/**
 * Convert hex text block to ByteArray
 * Concatenate all lines firstly, then convert to ByteArray
 * Example:
 * """
 * 01020
 * 304
 * """
 * to
 * [1, 2, 3, 4]
 */
fun CharSequence.hexBlockToByteArray(): ByteArray {
    val sb = StringBuilder()
    toString()
        .bufferedReader()
        .lines()
        .map { line ->
            sb.append(line)
        }
    return sb.hexToByteArray()
}

/**
 * Convert hex lines to ByteArray
 * Convert each line to ByteArray firstly, then concatenate all ByteArrays
 * Example:
 * """
 * 01020
 * 304
 * """
 * to
 * [0, 0x10, 0x20, 3, 4]
 */
fun CharSequence.hexLinesToByteArray(): ByteArray {
    val byteOS = ByteArrayOutputStream()
    toString()
        .bufferedReader()
        .lines()
        .forEach { line ->
            byteOS.writeBytes(line.hexToByteArray())
        }
    return byteOS.toByteArray()
}

fun CharSequence.base64Encode(srcCharset: Charset = Charsets.UTF_8): String {
    val data = toString().toByteArray(srcCharset)
    return Base64.getEncoder().encodeToString(data)
}

fun CharSequence.base64EncodeToByteArray(srcCharset: Charset = Charsets.UTF_8): ByteArray {
    val data = toString().toByteArray(srcCharset)
    return Base64.getEncoder().encode(data)
}

fun CharSequence.base64Decode(dstCharset: Charset = Charsets.UTF_8): String {
    return Base64.getDecoder().decode(toString()).toString(dstCharset)
}

fun CharSequence.base64DecodeToByteArray(): ByteArray {
    return Base64.getDecoder().decode(toString())
}

fun CharSequence.md5(srcCharset: Charset = Charsets.UTF_8): ByteArray {
    return Hash.MD5.hash(this, srcCharset)
}

fun CharSequence.sha1(srcCharset: Charset = Charsets.UTF_8): ByteArray {
    return Hash.SHA1.hash(this, srcCharset)
}

fun CharSequence.sha256(srcCharset: Charset = Charsets.UTF_8): ByteArray {
    return Hash.SHA256.hash(this, srcCharset)
}

fun CharSequence.isEmail(): Boolean {
    val regex =
        Regex("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))\$")
    return regex.matches(this)
}

fun String.inputStream(charset: Charset = Charsets.UTF_8): ByteArrayInputStream {
    return ByteArrayInputStream(toByteArray(charset))
}

fun String.bufferedReader(charset: Charset = Charsets.UTF_8): BufferedReader {
    return BufferedReader(InputStreamReader(ByteArrayInputStream(toByteArray(charset))))
}

