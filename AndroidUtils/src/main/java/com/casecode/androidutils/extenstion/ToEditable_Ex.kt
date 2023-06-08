package com.casecode.androidutils.extenstion

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)