package com.robelseyoum3.perseusprayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robelseyoum3.perseusprayer.Constants.Companion.ABOUT_ME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textId.text = ABOUT_ME
    }
}
