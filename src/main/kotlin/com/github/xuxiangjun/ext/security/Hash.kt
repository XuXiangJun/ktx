package com.github.xuxiangjun.ext.security

import java.io.File
import java.nio.charset.Charset
import java.security.MessageDigest

interface Hash {

    fun newDigest(): MessageDigest

    fun hash(data: ByteArray): ByteArray {
        return newDigest().digest(data)
    }

    fun hash(charSequence: CharSequence, charset: Charset = Charsets.UTF_8): ByteArray {
        return newDigest().digest(charSequence.toString().toByteArray(charset))
    }

    fun hash(file: File): ByteArray {
        return newDigest().digest(file)
    }
}
