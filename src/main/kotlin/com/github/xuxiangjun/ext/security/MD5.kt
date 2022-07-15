package com.github.xuxiangjun.ext.security

import java.security.MessageDigest

object MD5 : Hash {

    override fun newDigest(): MessageDigest {
        return MessageDigest.getInstance("md5")
    }
}
