package com.github.xuxiangjun.ext.security

import java.io.File
import java.nio.charset.Charset
import java.security.MessageDigest

interface Hash {

    val messageDigest: MessageDigest

    fun hash(data: ByteArray): ByteArray {
        return messageDigest.digest(data)
    }

    fun hash(charSequence: CharSequence, charset: Charset = Charsets.UTF_8): ByteArray {
        return messageDigest.digest(charSequence.toString().toByteArray(charset))
    }

    fun hash(file: File): ByteArray {
        return messageDigest.digest(file)
    }
}
