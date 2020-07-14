package com.robelseyoum3.perseusprayer.ui.main.manageplaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robelseyoum3.perseusprayer.R

class ManagePlacesFragment : BaseManagePlacesFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.manageplaces_fragment, container, false)
    }
}