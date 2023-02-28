package com.casecode.androidutilities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Android library for working with Log file in Android SDK.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}