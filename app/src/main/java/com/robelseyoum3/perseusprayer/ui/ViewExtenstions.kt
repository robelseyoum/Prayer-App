package com.robelseyoum3.perseusprayer.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes

//for string resource xml file @StringRes
fun Context.displayToast(@StringRes message: Int){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Context.displayToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Context.displayPrayerMethodDialog(prayerTimes: PrayerTimes){
//    val myItems = listOf("Hello", "World")
//    listItemsSingleChoice(items = myItems)


    MaterialDialog(this).show {
//        listItemsSingleChoice(items = prayerTimes)
    }
}