package com.casecode.androidutils.extenstion

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.isPermissionGranted(permission: String) = run {
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}