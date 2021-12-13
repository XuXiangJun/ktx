package com.github.xuxiangjun.javaext

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


