package com.robelseyoum3.perseusprayer.ui.components

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.robelseyoum3.perseusprayer.R

class TimelineView(context: Context?, layout:Int) : ConstraintLayout(context) {

    init {
        LayoutInflater.from(context).inflate(layout, this, true)
    }

    fun toggleColor(){

    }


}