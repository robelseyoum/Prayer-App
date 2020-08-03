package com.robelseyoum3.perseusprayer.ui.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.robelseyoum3.perseusprayer.R

class TimelineView(context: Context?, layout: Int, hidDot: Boolean = false) :
    ConstraintLayout(context) {

    init {
        val view = LayoutInflater.from(context).inflate(layout, this, true)

        if (hidDot) {
            view.findViewById<View>(R.id.dot).visibility = View.GONE
        }

    }

    fun toggleColor() {
        /**
         * change the color of dot
         */

//        findViewById<ImageView>(R.id.dot).colorFilter
    }


}