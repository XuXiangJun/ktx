package com.github.xuxiangjun.ext.security

import java.security.MessageDigest

object MD5 : Hash {
    override val messageDigest: MessageDigest
        get() = MessageDigest.getInstance("md5")
}
