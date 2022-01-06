package com.github.xuxiangjun.ext.security

import java.io.File
import java.nio.charset.Charset
import java.security.MessageDigest

interface Hash {
    object MD5 : Hash {
        override val messageDigest: MessageDigest
            get() = MessageDigest.getInstance("md5")
    }

    object SHA1 : Hash {
        override val messageDigest: MessageDigest
            get() = MessageDigest.getInstance("sha-1")
    }

    object SHA256 : Hash {
        override val messageDigest: MessageDigest
            get() = MessageDigest.getInstance("sha-256")
    }

    object SHA512 : Hash {
        override val messageDigest: MessageDigest
            get() = MessageDigest.getInstance("sha-512")
    }

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