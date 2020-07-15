package com.robelseyoum3.perseusprayer.ui.main.manageplaces

import android.os.Bundle
import android.util.Log
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "ManagePlacesFragment: ${viewModel.hashCode()}")
    }

}