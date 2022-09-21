package com.github.xuxiangjun.ext.security

import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AES {
    enum class Type {
        ECB,
        CBC,
        CFB,
        OFB,
        GCM
    }

    fun ecb(key: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(Type.ECB, padding, key)
    }

    fun cbc(key: ByteArray, iv: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(Type.CBC, padding, key, iv)
    }

    fun cfb(key: ByteArray, iv: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(Type.CFB, padding, key, iv)
    }

    fun ofb(key: ByteArray, iv: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(Type.OFB, padding, key, iv)
    }

    fun gcm(key: ByteArray, iv: ByteArray, padding: Padding = Padding.PKCS5Padding): AESCipher {
        return AESCipher(Type.GCM, padding, key, iv)
    }

    class AESCipher(
        private val type: Type,
        private val padding: Padding,
        private val key: ByteArray,
        private val iv: ByteArray? = null
    ) {
        private val transformation: String = "AES/${type.name}/${padding.name}"

        private fun getParams(): AlgorithmParameterSpec? {
            return when (type) {
                Type.ECB -> null
                Type.CBC -> IvParameterSpec(iv, 0, 16)
                Type.CFB -> IvParameterSpec(iv, 0, 16)
                Type.OFB -> IvParameterSpec(iv, 0, 16)
                Type.GCM -> GCMParameterSpec(128, iv, 0, 12)
            }
        }

        fun encrypt(data: ByteArray): ByteArray {
            val cipher = Cipher.getInstance(transformation)
            val secretKey = SecretKeySpec(key, "AES")

            val params = getParams()
            if (params == null) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, params)
            }

            return cipher.doFinal(data)
        }

        fun decrypt(data: ByteArray): ByteArray {
            val cipher = Cipher.getInstance(transformation)
            val secretKey = SecretKeySpec(key, "AES")
            val params = getParams()
            if (params == null) {
                cipher.init(Cipher.DECRYPT_MODE, secretKey)
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey, params)
            }

            return cipher.doFinal(data)
        }
    }
}
