package com.github.xuxiangjun.javaext

import java.io.File
import java.io.FileInputStream
import java.math.BigInteger
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.*
import javax.crypto.Cipher
import javax.crypto.KeyAgreement
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

fun MessageDigest.digest(file: File): ByteArray {
    var read = 0
    val buf = ByteArray(1024)
    FileInputStream(file).use {
        while (true) {
            read = it.read(buf)
            if (read == -1) {
                break
            }

            this.update(buf, 0, read)
        }
    }

    return digest()
}

object EC {
    enum class CurveName {
        secp112r1,
        secp112r2,
        secp128r1,
        secp128r2,
        secp160k1,
        secp160r1,
        secp160r2,
        secp192k1,
        secp192r1,
        secp224k1,
        secp224r1,
        secp256k1,
        secp256r1,
        secp384r1,
        secp521r1,
        sect113r1,
        sect113r2,
        sect131r1,
        sect131r2,
        sect163k1,
        sect163r1,
        sect163r2,
        sect193r1,
        sect193r2,
        sect233k1,
        sect233r1,
        sect239k1,
        sect283k1,
        sect283r1,
        sect409k1,
        sect409r1,
        sect571k1,
        sect571r1,
        ;
    }

    class ECKeyPair(
        val privateKey: ECPrivateKey,
        val publicKey: ECPublicKey,
    )

    fun getKeyPairGenerator(name: CurveName): KeyPairGenerator {
        val generator = KeyPairGenerator.getInstance("EC")
        val parameter = ECGenParameterSpec(name.name)
        generator.initialize(parameter)
        return generator
    }

    fun generateECKeyPair(name: CurveName): ECKeyPair {
        val generator = getKeyPairGenerator(name)
        val pair = generator.genKeyPair()
        return ECKeyPair(
            pair.private as ECPrivateKey,
            pair.public as ECPublicKey
        )
    }

    fun generateECPublicKey(x: BigInteger, y: BigInteger, param: ECParameterSpec): ECPublicKey {
        val factory = KeyFactory.getInstance("EC")
        val point = ECPoint(x, y)
        val keySpec = ECPublicKeySpec(point, param)
        return factory.generatePublic(keySpec) as ECPublicKey
    }

    fun generateECPublicKey(x: ByteArray, y: ByteArray, param: ECParameterSpec): ECPublicKey {
        val xInteger = ByteArray(x.size + 1).also {
            it[0] = 0
            System.arraycopy(x, 0, it, 1, x.size)
        }.let {
            BigInteger(it)
        }
        val yInteger = ByteArray(y.size + 1).also {
            it[0] = 0
            System.arraycopy(y, 0, it, 1, y.size)
        }.let {
            BigInteger(it)
        }
        return generateECPublicKey(xInteger, yInteger, param)
    }

    fun generateECPrivateKey(s: BigInteger, param: ECParameterSpec): ECPrivateKey {
        val factory = KeyFactory.getInstance("EC")
        val keySpec = ECPrivateKeySpec(s, param)
        return factory.generatePrivate(keySpec) as ECPrivateKey
    }

    fun generateECPrivateKey(s: ByteArray, param: ECParameterSpec): ECPrivateKey {
        val sInteger = ByteArray(s.size + 1).also {
            it[0] = 0
            System.arraycopy(s, 0, it, 1, s.size)
        }.let {
            BigInteger(it)
        }
        return generateECPrivateKey(sInteger, param)
    }

    fun generateECDHSecret(privateKey: ECPrivateKey, publicKey: ECPublicKey): ByteArray {
        val agreement = KeyAgreement.getInstance("ECDH")
        agreement.init(privateKey)
        agreement.doPhase(publicKey, true)
        return agreement.generateSecret()
    }
}

enum class Padding {
    NoPadding,
    PKCS5Padding,
    ISO10126Padding,
    ;
}

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

object RSA {
    class RSAKeyPair(
        val privateKey: RSAPrivateKey,
        val publicKey: RSAPublicKey
    )

    fun getKeyPairGenerator(keySize: Int): KeyPairGenerator {
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(keySize)
        return generator
    }

    fun generateRSAKeyPair(keySize: Int): RSAKeyPair {
        val generator = getKeyPairGenerator(keySize)
        val pair = generator.genKeyPair()
        return RSAKeyPair(
            pair.private as RSAPrivateKey,
            pair.public as RSAPublicKey
        )
    }

    fun generateRSAPublicKey(key: ByteArray): RSAPublicKey {
        val factory = KeyFactory.getInstance("RSA")
        val keySpec = X509EncodedKeySpec(key)
        return factory.generatePublic(keySpec) as RSAPublicKey
    }

    fun generateRSAPrivateKey(key: ByteArray): RSAPrivateKey {
        val factory = KeyFactory.getInstance("RSA")
        val keySpec = PKCS8EncodedKeySpec(key)
        return factory.generatePrivate(keySpec) as RSAPrivateKey
    }

    fun encrypt(data: ByteArray, publicKey: RSAPublicKey, transformation: String = "RSA"): ByteArray {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(data)
    }

    fun decrypt(data: ByteArray, privateKey: RSAPrivateKey, transformation: String = "RSA"): ByteArray {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(data)
    }
}
