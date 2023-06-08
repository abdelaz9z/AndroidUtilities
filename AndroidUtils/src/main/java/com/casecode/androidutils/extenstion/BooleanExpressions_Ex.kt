@file:OptIn(ExperimentalContracts::class)

package com.casecode.androidutils.extenstion

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun Boolean?.isTrue(): Boolean {
    contract {
        returns(true) implies (this@isTrue != null)
    }
    return this == true
}

private fun Boolean?.isFalse(): Boolean {
    contract {
        returns(true) implies (this@isFalse != null)
    }
    return this == false
}

val Boolean?.orTrue: Boolean
    get() = this ?: true

val Boolean?.orFalse: Boolean
    get() = this ?: false