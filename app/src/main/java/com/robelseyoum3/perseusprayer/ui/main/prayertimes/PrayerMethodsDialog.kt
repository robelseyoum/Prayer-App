package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.robelseyoum3.perseusprayer.R

class PrayerMethodsDialog : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.dialog_fragment, container, false)
        val cancelButton = rootView.findViewById<Button>(R.id.cancel_button)
        val submitButton = rootView.findViewById<Button>(R.id.submit_button)
        val methodRadioGroup = rootView.findViewById<RadioGroup>(R.id.myradiogroup)

        cancelButton.setOnClickListener { dismiss() }

        submitButton.setOnClickListener {
            val selectedId = methodRadioGroup.checkedRadioButtonId
            val selectedRadioButton = rootView.findViewById<RadioButton>(selectedId)
            Log.d("radio", selectedRadioButton.text.toString())
            dismiss()
        }

        return rootView
    }

}