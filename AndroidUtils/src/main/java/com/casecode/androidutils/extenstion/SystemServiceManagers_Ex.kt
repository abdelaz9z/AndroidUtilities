package com.casecode.androidutils.extenstion

import android.app.DownloadManager
import android.app.NotificationManager
import android.content.Context
import android.net.ConnectivityManager
import android.view.WindowManager
import androidx.core.content.ContextCompat

val Context.windowManager
    get() = ContextCompat.getSystemService(this, WindowManager::class.java)

val Context.connectivityManager
    get() = ContextCompat.getSystemService(this, ConnectivityManager::class.java)

val Context.notificationManager
    get() = ContextCompat.getSystemService(this, NotificationManager::class.java)

val Context.downloadManager
    get() = ContextCompat.getSystemService(this, DownloadManager::class.java)