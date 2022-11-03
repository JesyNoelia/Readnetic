package com.acoders.readnetic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acoders.readnetic.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadneticActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
