package com.easywallet.ext

fun String.clearHexPrefix(): String {
    return if (startsWith("0x")) removePrefix("0x") else this
}