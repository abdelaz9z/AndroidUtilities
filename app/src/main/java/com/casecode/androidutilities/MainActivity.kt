package com.casecode.androidutilities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.casecode.androidutilities.databinding.ActivityMainBinding
import com.casecode.androidutils.AndroidUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val versionName = AndroidUtils.getVersionName(this)
        binding.textView.text = versionName
    }
}