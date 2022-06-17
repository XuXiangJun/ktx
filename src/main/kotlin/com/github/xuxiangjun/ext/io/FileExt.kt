package com.github.xuxiangjun.ext.io

import com.github.xuxiangjun.ext.security.MD5
import com.github.xuxiangjun.ext.security.SHA1
import com.github.xuxiangjun.ext.security.SHA256
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

@Throws(FileNotFoundException::class)
fun File.toBufferReader(): BufferedReader {
    return BufferedReader(FileReader(this))
}

fun File.md5(): ByteArray {
    return MD5.hash(this)
}

fun File.sha1(): ByteArray {
    return SHA1.hash(this)
}

fun File.sha256(): ByteArray {
    return SHA256.hash(this)
}