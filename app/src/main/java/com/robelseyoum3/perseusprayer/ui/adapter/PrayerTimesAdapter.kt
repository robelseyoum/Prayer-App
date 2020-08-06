package com.robelseyoum3.perseusprayer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.AzanTime
import com.robelseyoum3.perseusprayer.ui.components.TimelineView
import kotlinx.android.synthetic.main.item_prayer_time.view.*
import kotlinx.android.synthetic.main.item_sunrise_time.view.*

class PrayerTimesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<AzanTime>()

    var data: List<AzanTime>
        get() = list.toList()
        set(value) {
            list.clear()
            list.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == TYPE_PRAYER_TIME){
            return PrayerTimeVH(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_prayer_time,
                    parent,
                    false
                )
            )
        } else {
            return SunRiseTimeVH(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_sunrise_time,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_PRAYER_TIME -> updatePrayerTime(holder as PrayerTimeVH, position)
            TYPE_SUNRISE -> updateSunriseTime(holder as SunRiseTimeVH, position)
        }
    }
//
//    private fun isInTheRange(azanTime: AzanTime): Boolean {
//        /**
//         * Add all prayers logic as well as sunrise time.
//         */
//        when(azanTime.name.toLowerCase()){
//            "fajar" -> {
//                //fajar>= current time <= sunRiseTime
//                return  true
//            }
//            "isha" -> {
//                //Isha>= current time <= 11:59 pm
//                return  true
//            }
//        }
//
//        return false
//    }

    private fun updateSunriseTime(holder: SunRiseTimeVH, position: Int) {
        val timeLineView =
            TimelineView(
                holder.itemView.context,
                null,
                0,
                R.layout.view_time_line,
                true
            )
        holder.timeLineContainer.addView(timeLineView)
        holder.time.text = list[position].time


//        if(isInTheRange(list[position])){
//
//        }

    }

    override fun getItemViewType(position: Int): Int =
        if(list[position].type == TYPE_PRAYER_TIME){
            TYPE_PRAYER_TIME
        } else {
            TYPE_SUNRISE
        }

    private fun updatePrayerTime(holder: PrayerTimeVH, position: Int) {
        when (position) {
            0 -> {
                val timeLineView = TimelineView(holder.itemView.context, null, 0, R.layout.view_time_line, true)
                holder.timeLineContainer.addView(timeLineView)
                holder.name.text = list[position].name
                holder.time.text = list[position].time

                /**
                 * if the time is between fajar time and sunrise time.
                 * fajar>= current time <= sunRiseTime
                 */

//                if(isInTheRange(list[position])) {
//                    timeLineView.toggleColor()
//                }
            }

            list.size - 1 -> {
                val timeLineView = TimelineView(holder.itemView.context, null, 0, R.layout.view_time_line, true)
                holder.timeLineContainer.addView(timeLineView)
                holder.name.text = list[position].name
                holder.time.text = list[position].time

//                if(isInTheRange(list[position])) {
//                    timeLineView.toggleColor()
//                }
            }

            else -> {
                val timeLineView = TimelineView(holder.itemView.context, null, 0, R.layout.view_time_line, true)
                holder.timeLineContainer.addView(timeLineView)
                holder.name.text = list[position].name
                holder.time.text = list[position].time

//                if(isInTheRange(list[position])) {
//                    timeLineView.toggleColor()
//                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class PrayerTimeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeLineContainer: LinearLayout = itemView.prayerTimelineContainer
        val name: TextView = itemView.prayerName
        val time: TextView = itemView.prayerTime
    }

    class SunRiseTimeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeLineContainer: LinearLayout = itemView.sunrisePrayerTimelineContainer
        val time: TextView = itemView.prayerSunrise
    }

    companion object {
        const val TYPE_PRAYER_TIME: Int = 0
        const val TYPE_SUNRISE: Int = 1
    }
}
