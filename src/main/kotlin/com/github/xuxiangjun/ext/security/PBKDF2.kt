package com.github.xuxiangjun.ext.security

import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PBKDF2 {

    private fun genSecretKey(algorithm: String, keySpec: PBEKeySpec): SecretKey {
        val factory = SecretKeyFactory.getInstance(algorithm)
        return factory.generateSecret(keySpec)
    }

    fun hmacSHA1(password: CharArray, salt: ByteArray, iterationCount: Int, keyLength: Int): ByteArray {
        val spec = PBEKeySpec(password, salt, iterationCount, keyLength)
        val secret = genSecretKey("PBKDF2WithHmacSHA1", spec)
        return secret.encoded
    }

    fun hmacSHA256(password: CharArray, salt: ByteArray, iterationCount: Int, keyLength: Int): ByteArray {
        val spec = PBEKeySpec(password, salt, iterationCount, keyLength)
        val secret = genSecretKey("PBKDF2WithHmacSHA256", spec)
        return secret.encoded
    }

    fun hmacSHA512(password: CharArray, salt: ByteArray, iterationCount: Int, keyLength: Int): ByteArray {
        val spec = PBEKeySpec(password, salt, iterationCount, keyLength)
        val secret = genSecretKey("PBKDF2WithHmacSHA512", spec)
        return secret.encoded
    }
}