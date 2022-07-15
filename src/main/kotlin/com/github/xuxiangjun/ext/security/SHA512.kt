package com.github.xuxiangjun.ext.security

import java.security.MessageDigest

object SHA512 : Hash {
    override fun newDigest(): MessageDigest {
        return MessageDigest.getInstance("sha-512")
    }
}
