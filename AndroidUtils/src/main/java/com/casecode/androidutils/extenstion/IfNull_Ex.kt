package com.casecode.androidutils.extenstion

fun Any?.ifNull(block: () -> Unit) = run {
    if (this == null) {
        block()
    }
}