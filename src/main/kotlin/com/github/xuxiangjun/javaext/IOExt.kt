package com.github.xuxiangjun.javaext

import java.io.Closeable
import java.io.InputStream

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

fun InputStream.readUInt8(): Int {
    return read() and 0xff
}

fun InputStream.readUInt16(): Int {
    return (read() and 0xff) or
            ((read() and 0xff) shl 8)
}

fun InputStream.readUInt32(): Long {
    return (read().toLong() and 0xffL) or
            ((read().toLong() and 0xffL) shl 8) or
            ((read().toLong() and 0xffL) shl 16) or
            ((read().toLong() and 0xffL) shl 24)
}

fun InputStream.readInt64(): Long {
    return (read().toLong() and 0xffL) or
            ((read().toLong() and 0xffL) shl 8) or
            ((read().toLong() and 0xffL) shl 16) or
            ((read().toLong() and 0xffL) shl 24) or
            ((read().toLong() and 0xffL) shl 32) or
            ((read().toLong() and 0xffL) shl 40) or
            ((read().toLong() and 0xffL) shl 48) or
            ((read().toLong() and 0xffL) shl 56)
}
