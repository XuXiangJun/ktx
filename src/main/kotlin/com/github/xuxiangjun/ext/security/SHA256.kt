package com.github.xuxiangjun.ext.security

import java.security.MessageDigest

object SHA256 : Hash {
    override val messageDigest: MessageDigest
        get() = MessageDigest.getInstance("sha-256")
}
