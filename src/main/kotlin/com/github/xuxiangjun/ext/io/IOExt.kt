package com.github.xuxiangjun.ext.io

import com.github.xuxiangjun.ext.security.Hash
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

@Throws(FileNotFoundException::class)
fun File.toBufferReader(): BufferedReader {
    return BufferedReader(FileReader(this))
}

fun File.md5(): ByteArray {
    return Hash.MD5.hash(this)
}

fun File.sha1(): ByteArray {
    return Hash.SHA1.hash(this)
}

fun File.sha256(): ByteArray {
    return Hash.SHA256.hash(this)
}
