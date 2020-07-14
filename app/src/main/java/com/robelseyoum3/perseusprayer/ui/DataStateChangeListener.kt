package com.robelseyoum3.perseusprayer.ui

import com.robelseyoum3.perseusprayer.utils.DataState

interface DataStateChangeListener {
    fun onDataStateChange(dataState: DataState<*>?) //type invariant or any type
}