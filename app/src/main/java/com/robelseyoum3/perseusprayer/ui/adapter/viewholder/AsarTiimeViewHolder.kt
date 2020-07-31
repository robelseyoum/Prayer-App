package com.robelseyoum3.perseusprayer.ui.adapter.viewholder

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import kotlinx.android.synthetic.main.asar_time.view.*


class AsarTiimeViewHolder (item: View): RecyclerView.ViewHolder(item) {

    val asarTimeline: ImageView = item.circle_line_timeline_asar

    fun bind(prayerTimes: PrayerTimes){
        itemView.asar_time.text = prayerTimes.mAsr.toString()
//        asarTimeline.setImageDrawable(Uri.parse("android.resource://"+ BuildConfig.APPLICATION_ID R.drawable.pointer)
        Log.d("AsarTiimeViewHolder", "$prayerTimes.mAsr")

    }

}