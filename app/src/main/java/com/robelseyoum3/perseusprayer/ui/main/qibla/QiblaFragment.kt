package com.robelseyoum3.perseusprayer.ui.main.qibla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robelseyoum3.perseusprayer.R

class QiblaFragment : BaseQiblaFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.qibla_fragment, container, false)
    }
}