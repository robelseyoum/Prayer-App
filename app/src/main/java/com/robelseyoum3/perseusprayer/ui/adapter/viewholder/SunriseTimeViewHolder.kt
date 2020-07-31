package com.robelseyoum3.perseusprayer.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import kotlinx.android.synthetic.main.sunrise_time.view.*


class SunriseTimeViewHolder (item: View): RecyclerView.ViewHolder(item) {

    val sunriseTime: TextView = item.sunrise_text

    fun bind(prayerTimes: PrayerTimes){
        itemView.sunrise_text.text = prayerTimes.mSunrise
    }

}
