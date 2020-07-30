package com.robelseyoum3.perseusprayer.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import kotlinx.android.synthetic.main.maghrib_time.view.*

class MaghribTimeViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val maghribTime: TextView = item.maghrib_time
    val maghribTimeLine: ImageView = item.circle_line_timeline_maghrib

    fun bind(prayerTimes: PrayerTimes){
        itemView.maghrib_time.text = prayerTimes.mFajr
    }

}