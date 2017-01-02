package com.example.yurich.tuturutest.repository.local_storage

import com.example.yurich.tuturutest.repository.remote_storage.ResponseStation
import com.example.yurich.tuturutest.utils.DirectionConstants
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by yurich on 22.12.16.
 */
open class StoragedStation(
        open var countryTitle: String = "",
        open var districtTitle: String? = null,
        open var cityId: Long = 0,
        open var cityTitle: String = "",
        open var regionTitle: String? = null,

        open var stationId: Long = 0,
        open var stationTitle: String = "",

        open var direction: Int = DirectionConstants.DEPARTURE,
        @PrimaryKey
        open var recordId: Long = stationId shl 1 or direction.toLong()
) : RealmObject() {

    constructor(station: ResponseStation, direction: Int = DirectionConstants.DEPARTURE) :
            this(
                    station.countryTitle,
                    station.districtTitle,
                    station.cityId,
                    station.cityTitle,
                    station.regionTitle,

                    station.stationId,
                    station.stationTitle,

                    direction
            )

    fun copy(station: StoragedStation) {
        this.countryTitle = station.countryTitle
        this.districtTitle = station.districtTitle
        this.cityId = station.cityId
        this.cityTitle = station.cityTitle
        this.regionTitle = station.regionTitle

        this.stationTitle = station.stationTitle

        this.direction = station.direction
    }
}