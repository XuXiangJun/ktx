package com.github.xuxiangjun.ext.security

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HMAC {
    private fun genMac(key: ByteArray, algorithm: String): Mac {
        val mac = Mac.getInstance(algorithm)
        val spec = SecretKeySpec(key, algorithm)
        mac.init(spec)
        return mac
    }

    private fun hmacSHA1(key: ByteArray): Mac {
        return genMac(key, "HmacSHA1")
    }

    private fun hmacSHA256(key: ByteArray): Mac {
        return genMac(key, "HmacSHA256")
    }

    private fun hmacSHA512(key: ByteArray): Mac {
        return genMac(key, "HmacSHA512")
    }

    fun sha1(key: ByteArray, data: ByteArray): ByteArray {
        return hmacSHA1(key).doFinal(data)
    }

    fun sha256(key: ByteArray, data: ByteArray): ByteArray {
        return hmacSHA256(key).doFinal(data)
    }

    fun sha512(key: ByteArray, data: ByteArray): ByteArray {
        return hmacSHA512(key).doFinal(data)
    }
}