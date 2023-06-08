package com.casecode.androidutils.extenstion

import android.widget.EditText

val EditText.value
    get() = text?.toString() ?: ""