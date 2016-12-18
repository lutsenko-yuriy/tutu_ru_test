package com.example.yurich.tuturutest.repository.local_storage

import android.text.TextUtils
import com.example.yurich.tuturutest.mvp.SchedulePresenter
import com.example.yurich.tuturutest.repository.local_storage.database.StationsTable
import com.example.yurich.tuturutest.repository.remote_storage.ResponseCity
import com.example.yurich.tuturutest.repository.remote_storage.ResponseStation
import com.example.yurich.tuturutest.utils.DirectionConstants.DEPARTURE
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType

/**
 * The StoragedStation entity will be stored at databases
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
        val cityTitle: String,
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

    constructor(station: StoragedStation) :
            this(
                    station.countryTitle,
                    station.districtTitle,
                    station.cityId,
                    station.cityTitle,
                    station.regionTitle
            )

    override fun getType(): Int = Types.CITY

    fun getAddress(): String {
        val builder = StringBuilder()

        appendField(countryTitle, builder)
        appendField(cityTitle, builder)

        return builder.toString().substring(2)
    }

    override fun equals(other: Any?): Boolean {
        return other is StoragedCity
                && this.cityId == other.cityId
    }
}

@StorIOSQLiteType(table = StationsTable.TABLE)
class StoragedStation(
        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_COUNTRY)
        val countryTitle: String,
        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_DISTRICT)
        val districtTitle: String?,
        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_CITY_ID)
        val cityId: Long,
        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_CITY)
        val cityTitle: String,
        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_REGION)
        val regionTitle: String?,

        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_ID)
        val stationId: Long,
        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_STATION)
        val stationTitle: String,

        @get:StorIOSQLiteColumn(name = StationsTable.COLUMN_DIRECTION)
        val direction: Int = DEPARTURE
) : StoragedEntity(){

    constructor(station: ResponseStation, direction: Int = DEPARTURE) :
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

    override fun getType() = Types.STATION

    fun getAddress(): String {

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

}


// Helper function to generate address
fun appendField(field: String?, builder: StringBuilder) {
    if (!TextUtils.isEmpty(field)) {
        builder
                .append(", ")
                .append(field)
    }
}