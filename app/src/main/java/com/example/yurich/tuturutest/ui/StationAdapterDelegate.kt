package com.example.yurich.tuturutest.ui

import android.content.Context.VIBRATOR_SERVICE
import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.utils.inflate
import kotlinx.android.synthetic.main.item_station.view.*

/**
 * An adapter delegate for single station
 */
class StationAdapterDelegate() : AbstractAdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return StationHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: StoragedEntity) {
        (holder as StationHolder).bind(item as StoragedStation)
    }

    inner class StationHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_station)
    ) {
        fun bind(item: StoragedStation) = with(itemView) {
            setOnClickListener {
               val vibrator =
                       context.getSystemService(VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(10)
            }
            station_title.text = item.stationTitle
            address_header.text = item.getAddress()
            country.text = item.countryTitle
        }
    }
}