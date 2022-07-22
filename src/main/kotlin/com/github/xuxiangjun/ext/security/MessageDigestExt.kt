package com.github.xuxiangjun.ext.security

import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

fun MessageDigest.digest(file: File): ByteArray {
    var read = 0
    val buf = ByteArray(4096)
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