package com.example.yurich.tuturutest.ui

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.network.Station
import kotlinx.android.synthetic.main.item_station.view.*

/**
 * Created by yurich on 15.12.16.
 */
class StationModel (
        val station: Station
) : EpoxyModelWithHolder<StationModel.StationHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.item_station
    }

    override fun createNewHolder(): StationHolder {
        return StationHolder(station)
    }

    inner class StationHolder(
            val station: Station?
    ) : EpoxyHolder() {

        override fun bindView(itemView: View?) {
            itemView?.apply {
                station_title.text = station?.stationTitle
                country.text = station?.countryTitle
                country_address_header.text = station?.getAddress()
            }
        }
    }
}