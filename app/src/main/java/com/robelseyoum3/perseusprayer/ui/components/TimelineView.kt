package com.robelseyoum3.perseusprayer.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.robelseyoum3.perseusprayer.R

class TimelineView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, layout: Int, hidDot:Boolean
) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(layout, this, true)

        if(hidDot){
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