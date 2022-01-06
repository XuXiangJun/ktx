package com.github.xuxiangjun.ext.security

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AES {
    fun ecb(key: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(key, "AES/ECB/${padding.name}")
    }

    fun cbc(key: ByteArray, iv: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(key, "AES/CBC/${padding.name}", iv)
    }

    fun cfb(key: ByteArray, iv: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(key, "AES/CFB/${padding.name}", iv)
    }

    fun ofb(key: ByteArray, iv: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(key, "AES/OFB/${padding.name}", iv)
    }

    class AESCipher(
        private val key: ByteArray,
        private val transformation: String,
        private val iv: ByteArray? = null
    ) {
        fun encrypt(data: ByteArray): ByteArray {
            val cipher = Cipher.getInstance(transformation)
            val secretKey = SecretKeySpec(key, "AES")
            if (iv == null) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            } else {
                val ivParam = IvParameterSpec(iv)
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParam)
            }

            return cipher.doFinal(data)
        }

        fun decrypt(data: ByteArray): ByteArray {
            val cipher = Cipher.getInstance(transformation)
            val secretKey = SecretKeySpec(key, "AES")
            if (iv == null) {
                cipher.init(Cipher.DECRYPT_MODE, secretKey)
            } else {
                val ivParam = IvParameterSpec(iv)
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParam)
            }

            return cipher.doFinal(data)
        }
    }
}