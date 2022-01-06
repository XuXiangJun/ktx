package com.github.xuxiangjun.ext.security

import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

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