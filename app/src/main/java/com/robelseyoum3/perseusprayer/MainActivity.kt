package com.robelseyoum3.perseusprayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robelseyoum3.perseusprayer.Constants.Companion.ABOUT_ME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createName()
        printName()
    }

    private fun printName() {
        textId.text = person.name
    }

    private fun createName() {
        person = Person("Robel Seyoum")
    }
}
