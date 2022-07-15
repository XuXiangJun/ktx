package com.github.xuxiangjun.ext.security

import java.security.MessageDigest

object SHA1 : Hash {

    override fun newDigest(): MessageDigest {
        return MessageDigest.getInstance("sha-1")
    }
}
