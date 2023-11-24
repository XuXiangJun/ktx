package com.github.xuxiangjun.ext.io

import java.io.Closeable
import java.io.File
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

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

class ZipParams {
    var level: Int? = null
    var comment: String? = null
    var method: Int? = null
}

fun zipFolder(
    folder: File,
    output: OutputStream,
    block: ((ZipParams) -> Unit)? = null
) {
    if (!folder.isDirectory) {
        throw IllegalArgumentException("${folder.path} is not a folder")
    }
    val zipParams = ZipParams()
    block?.invoke(zipParams)
    val folderPath = folder.path
    val folderName = folder.name
    ZipOutputStream(output).use { zipOS ->
        zipParams.level?.let {
            zipOS.setLevel(it)
        }
        zipParams.comment?.let {
            zipOS.setComment(it)
        }
        zipParams.method?.let {
            zipOS.setMethod(it)
        }

        folder.walk()
            .filter { it.isFile }
            .forEach {
                val pathInZip = "$folderName${it.path.substring(folderPath.length)}"
                zipOS.putNextEntry(ZipEntry(pathInZip))
                zipOS.write(it.readBytes())
                zipOS.closeEntry()
            }

    }
}
