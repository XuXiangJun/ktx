package com.github.xuxiangjun.ext.io

import com.github.xuxiangjun.ext.security.MD5
import com.github.xuxiangjun.ext.security.SHA1
import com.github.xuxiangjun.ext.security.SHA256
import java.io.*

fun uses(vararg args: Closeable, block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        for (c in args) {
            try {
                c.close()
            } catch (ignore: Exception) {
            }
        }
    }
}
