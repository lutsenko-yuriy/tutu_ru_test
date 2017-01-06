package com.example.yurich.tuturutest.schedule.ScheduleFragment

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.utils.DirectionConstants.DEPARTURE
import java.io.Serializable

/**
 * The DisplayedStation entity will be stored at databases
 * and used in stations lists' adapter
 */
abstract class DisplayedEntity() {
    companion object Types {
        val CITY = 0
        val STATION = 1
    }

    abstract fun getType(): Int

    abstract fun getAddress(): String

    abstract fun getEntityId(): Long
}

class DisplayedCity(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String,
        val regionTitle: String?
) : DisplayedEntity() {

    constructor(station: DisplayedStation) :
            this(
                    station.countryTitle,
                    station.districtTitle,
                    station.cityId,
                    station.cityTitle,
                    station.regionTitle
            )

    override fun getType(): Int = CITY

    override fun getAddress(): String {
        val builder = StringBuilder()

        appendField(countryTitle, builder)
        appendField(cityTitle, builder)

        return builder.toString().substring(2)
    }

    override fun getEntityId(): Long {
        return cityId shl 1 or getType().toLong()
    }
}

class DisplayedStation(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String,
        val regionTitle: String?,

        val stationId: Long,
        val stationTitle: String,

        val direction: Int = DEPARTURE
) : DisplayedEntity() {

    constructor(station: StoragedStation) :
            this(
                    station.countryTitle,
                    station.districtTitle,
                    station.cityId,
                    station.cityTitle,
                    station.regionTitle,

                    station.stationId,
                    station.stationTitle,

                    station.direction
            )

    override fun getType() = STATION

    override fun getAddress(): String {

        val builder = StringBuilder()

        appendField(districtTitle, builder)
        appendField(cityTitle, builder)
        appendField(regionTitle, builder)

        return builder.toString().substring(2)
    }

    fun getFullAddress(): String {
        val builder = StringBuilder()

        appendField(stationTitle, builder)
        appendField(districtTitle, builder)
        appendField(cityTitle, builder)
        appendField(regionTitle, builder)
        appendField(countryTitle, builder)

        return builder.toString().substring(2)
    }

    override fun getEntityId(): Long {
        return stationId shl 1 or getType().toLong()
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