package com.github.xuxiangjun.ext.security

import com.github.xuxiangjun.ext.base.toUBigInteger
import java.math.BigInteger
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PublicKey
import javax.crypto.KeyAgreement
import javax.crypto.interfaces.DHPrivateKey
import javax.crypto.interfaces.DHPublicKey
import javax.crypto.spec.DHParameterSpec
import javax.crypto.spec.DHPrivateKeySpec
import javax.crypto.spec.DHPublicKeySpec


object DH {
    private const val algorithm = "DH"

    class DHKeyPair(
        val privateKey: DHPrivateKey,
        val publicKey: DHPublicKey,
    )

    private fun getKeyPair(p: BigInteger?, g: BigInteger?, length: Int): DHKeyPair {
        val generator = KeyPairGenerator.getInstance(algorithm)
        if (p == null && g == null) {
            generator.initialize(length)
        } else {
            val parameter = DHParameterSpec(p, g, length)
            generator.initialize(parameter)
        }

        val pair = generator.genKeyPair()
        return DHKeyPair(
            pair.private as DHPrivateKey,
            pair.public as DHPublicKey
        )
    }

    fun generateECKeyPair(length: Int): DHKeyPair {
        return getKeyPair(null, null, length)
    }

    fun generateECKeyPair(p: BigInteger, g: BigInteger, length: Int): DHKeyPair {
        return getKeyPair(p, g, length)
    }

    fun generateECKeyPair(p: ByteArray, g: ByteArray, length: Int): DHKeyPair {
        return getKeyPair(p.toUBigInteger(), g.toUBigInteger(), length)
    }

    fun generateDHPublicKey(y: BigInteger, p: BigInteger, g: BigInteger): DHPublicKey {
        val spec = DHPublicKeySpec(y, p, g)
        val keyFact = KeyFactory.getInstance(algorithm)
        return keyFact.generatePublic(spec) as DHPublicKey
    }

    fun generateDHPublicKey(y: ByteArray, p: BigInteger, g: BigInteger): DHPublicKey {
        return generateDHPublicKey(y.toUBigInteger(), p, g)
    }

    fun generateDHPrivateKey(x: BigInteger, p: BigInteger, g: BigInteger): DHPrivateKey {
        val spec = DHPrivateKeySpec(x, p, g)
        val keyFact = KeyFactory.getInstance(algorithm)
        return keyFact.generatePrivate(spec) as DHPrivateKey
    }

    fun generateDHPrivateKey(x: ByteArray, p: BigInteger, g: BigInteger): DHPrivateKey {
        return generateDHPrivateKey(x.toUBigInteger(), p, g)
    }

    fun generateDHSecret(privateKey: DHPrivateKey, publicKey: DHPublicKey): ByteArray {
        val ka = KeyAgreement.getInstance(algorithm)
        ka.init(privateKey)
        ka.doPhase(publicKey, true)
        return ka.generateSecret()
    }
}