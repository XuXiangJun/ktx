package com.github.xuxiangjun.ext.io

import java.io.*

fun Serializable.toObjectByteArray(): ByteArray {
    val byteArrayOS = ByteArrayOutputStream()
    ObjectOutputStream(byteArrayOS).use {
        it.writeObject(this)
        it.flush()
    }
    return byteArrayOS.toByteArray()
}

fun <T: Serializable> ByteArray.toSerializable(): T {
    return ObjectInputStream(ByteArrayInputStream(this)).use {
        @Suppress("UNCHECKED_CAST")
        it.readObject() as T
    }
}
