package com.example.yurich.tuturutest.ui

import com.airbnb.epoxy.EpoxyAdapter
import com.example.yurich.tuturutest.network.City

/**
 * Created by yurich on 15.12.16.
 */
class StationsAdapter() : EpoxyAdapter() {

    val loadingModel = LoadingModel()

    init {
        addModel(loadingModel)
    }

    fun addStations(cities: List<City>) {
        hideModel(loadingModel)

        for (city in cities) {
            addModel(HeaderModel(city))

            for (station in city.stations!!) {
                insertModelAfter(StationModel(station), loadingModel)
            }
        }
    }

}