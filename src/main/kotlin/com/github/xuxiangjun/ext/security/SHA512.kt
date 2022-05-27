package com.github.xuxiangjun.ext.security

import java.security.MessageDigest

object SHA512 : Hash {
    override val messageDigest: MessageDigest
        get() = MessageDigest.getInstance("sha-512")
}
