package com.github.xuxiangjun.ext.security

import com.github.xuxiangjun.ext.base.toUBigInteger
import java.math.BigInteger
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.*
import javax.crypto.KeyAgreement

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

    private fun getKeyPairGenerator(name: CurveName): KeyPairGenerator {
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
        val xInteger = x.toUBigInteger()
        val yInteger = y.toUBigInteger()
        return generateECPublicKey(xInteger, yInteger, param)
    }

    fun generateECPrivateKey(s: BigInteger, param: ECParameterSpec): ECPrivateKey {
        val factory = KeyFactory.getInstance("EC")
        val keySpec = ECPrivateKeySpec(s, param)
        return factory.generatePrivate(keySpec) as ECPrivateKey
    }

    fun generateECPrivateKey(s: ByteArray, param: ECParameterSpec): ECPrivateKey {
        val sInteger = s.toUBigInteger()
        return generateECPrivateKey(sInteger, param)
    }

    fun generateECDHSecret(privateKey: ECPrivateKey, publicKey: ECPublicKey): ByteArray {
        val agreement = KeyAgreement.getInstance("ECDH")
        agreement.init(privateKey)
        agreement.doPhase(publicKey, true)
        return agreement.generateSecret()
    }
}