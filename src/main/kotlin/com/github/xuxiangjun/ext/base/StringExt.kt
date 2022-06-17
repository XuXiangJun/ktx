package com.github.xuxiangjun.ext.base

import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

fun String.toInputStream(charset: Charset = Charsets.UTF_8): ByteArrayInputStream {
    return ByteArrayInputStream(toByteArray(charset))
}

fun String.toBufferedReader(charset: Charset = Charsets.UTF_8): BufferedReader {
    return BufferedReader(InputStreamReader(ByteArrayInputStream(toByteArray(charset))))
}