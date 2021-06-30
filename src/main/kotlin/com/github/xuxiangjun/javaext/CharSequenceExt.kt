package com.github.xuxiangjun.javaext

import java.nio.charset.Charset
import java.security.MessageDigest
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

fun CharSequence.base64Encode(): String {
    return Base64.getEncoder().encodeToString(toString().toByteArray(Charsets.UTF_8))
}

fun CharSequence.base64EncodeToByteArray(): ByteArray {
    return Base64.getEncoder().encode(toString().toByteArray(Charsets.UTF_8))
}

fun CharSequence.base64Decode(): String {
    return Base64.getDecoder().decode(toString()).toString(Charsets.UTF_8)
}

fun CharSequence.base64DecodeToByteArray(): ByteArray {
    return Base64.getDecoder().decode(toString())
}

fun CharSequence.md5(
    charset: Charset = Charsets.UTF_8,
): ByteArray {
    val md = MessageDigest.getInstance("md5")
    return md.digest(toString().toByteArray(charset))
}

fun CharSequence.sha1(
    charset: Charset = Charsets.UTF_8,
): ByteArray {
    val md = MessageDigest.getInstance("sha-1")
    return md.digest(toString().toByteArray(charset))
}

fun CharSequence.sha256(
    charset: Charset = Charsets.UTF_8,
): ByteArray {
    val md = MessageDigest.getInstance("sha-256")
    return md.digest(toString().toByteArray(charset))
}


