package com.example.yurich.tuturutest.ui.adapters

import android.content.Context
import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import com.example.yurich.tuturutest.ui.OnStationListener
import com.example.yurich.tuturutest.utils.inflate
import kotlinx.android.synthetic.main.item_station.view.*

/**
 * An adapter delegate for single station
 */
class StationAdapterDelegate(val listener: OnStationListener) : AbstractAdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return StationHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DisplayedEntity) {
        (holder as StationHolder).bind(item as DisplayedStation)
    }

    inner class StationHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_station)
    ) {
        fun bind(item: DisplayedStation) = with(itemView) {
            setOnClickListener {
                (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
                        .vibrate((10))
                listener.onStationClicked(item)
            }
            station_title.text = item.stationTitle
            address_header.text = item.getAddress()
            country.text = item.countryTitle
        }
    }
}