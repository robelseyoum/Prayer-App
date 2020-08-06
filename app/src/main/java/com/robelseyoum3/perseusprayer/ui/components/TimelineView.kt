package com.robelseyoum3.perseusprayer.ui.components

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.robelseyoum3.perseusprayer.R

class TimelineView(context: Context?, layout:Int, hiDot: Boolean = false) : ConstraintLayout(context) {

    init {
        LayoutInflater.from(context).inflate(layout, this, true)

        if(hiDot){
            findViewById<View>(R.id.dot).visibility = View.GONE
        }

    }

    fun toggleColor(){
        /**
         * Change the color of dot
         */
        findViewById<ImageView>(R.id.dot).setColorFilter(R.color.tealDark)
    }


}
