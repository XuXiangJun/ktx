package com.github.xuxiangjun.ext.io

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

fun InputStream.readUInt8(): Int {
    return read() and 0xff
}

fun InputStream.readUInt16(littleEndian: Boolean = true): Int {
    return if (littleEndian) {
        (read() and 0xff) or
                ((read() and 0xff) shl 8)
    } else {
        ((read() and 0xff) shl 8) or
                (read() and 0xff)
    }
}

fun InputStream.readUInt32(littleEndian: Boolean = true): Long {
    return if (littleEndian) {
        (read().toLong() and 0xffL) or
                ((read().toLong() and 0xffL) shl 8) or
                ((read().toLong() and 0xffL) shl 16) or
                ((read().toLong() and 0xffL) shl 24)
    } else {
        ((read().toLong() and 0xffL) shl 24) or
                ((read().toLong() and 0xffL) shl 16) or
                ((read().toLong() and 0xffL) shl 8) or
                (read().toLong() and 0xffL)
    }
}

fun InputStream.readInt64(littleEndian: Boolean = true): Long {
    return if (littleEndian) {
        (read().toLong() and 0xffL) or
                ((read().toLong() and 0xffL) shl 8) or
                ((read().toLong() and 0xffL) shl 16) or
                ((read().toLong() and 0xffL) shl 24) or
                ((read().toLong() and 0xffL) shl 32) or
                ((read().toLong() and 0xffL) shl 40) or
                ((read().toLong() and 0xffL) shl 48) or
                ((read().toLong() and 0xffL) shl 56)
    } else {
        ((read().toLong() and 0xffL) shl 56) or
                ((read().toLong() and 0xffL) shl 48) or
                ((read().toLong() and 0xffL) shl 40) or
                ((read().toLong() and 0xffL) shl 32) or
                ((read().toLong() and 0xffL) shl 24) or
                ((read().toLong() and 0xffL) shl 16) or
                ((read().toLong() and 0xffL) shl 8) or
                (read().toLong() and 0xffL)
    }
}

fun InputStream.toBufferedReader(): BufferedReader {
    return BufferedReader(InputStreamReader(this))
}