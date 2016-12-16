package com.example.yurich.tuturutest.repository.database

import android.text.TextUtils
import com.example.yurich.tuturutest.repository.network.ResponseCity
import com.example.yurich.tuturutest.repository.network.ResponseStation

/**
 * These entities will be stored at databases
 * and used in stations lists' adapter
 */
abstract class StoragedEntity() {
    companion object Types {
        val CITY = 0
        val STATION = 1
    }

    abstract fun getType(): Int
}

class StoragedCity(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String?,
        val regionTitle: String?
) : StoragedEntity() {

    constructor(city: ResponseCity) :
            this(
                    city.countryTitle,
                    city.districtTitle,
                    city.cityId,
                    city.cityTitle,
                    city.regionTitle
            )

    override fun getType(): Int = Types.CITY

    fun getAddress(): String {
        val builder = StringBuilder()

        appendField(countryTitle, builder)
        appendField(cityTitle, builder)

        return builder.toString().substring(2)
    }
}


class StoragedStation(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String?,
        val regionTitle: String?,

        val stationId: Long,
        val stationTitle: String,

        val isDeparture: Boolean = true
) : StoragedEntity(){

    constructor(station: ResponseStation, isDeparture: Boolean = true) :
            this(
                    station.countryTitle,
                    station.districtTitle,
                    station.cityId,
                    station.cityTitle,
                    station.regionTitle,

                    station.stationId,
                    station.stationTitle,

                    isDeparture
            )

    override fun getType() = Types.STATION

    fun getAddress(): String {

        val builder = StringBuilder()

        appendField(districtTitle, builder)
        appendField(cityTitle, builder)
        appendField(regionTitle, builder)

        return builder.toString().substring(2)
    }

}


// Helper function to generate address
fun appendField(field: String?, builder: StringBuilder) {
    if (!TextUtils.isEmpty(field)) {
        builder
                .append(", ")
                .append(field)
    }
}