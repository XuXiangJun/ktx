package com.github.xuxiangjun.ext.io

import java.io.Closeable

fun uses(vararg args: Closeable, block: () -> Unit) {
    try {
        block()
    } finally {
        for (c in args) {
            try {
                c.close()
            } catch (ignore: Exception) {
            }
        }
    }
}
