package com.example.yurich.tuturutest.repository.local_storage

import android.text.TextUtils
import com.example.yurich.tuturutest.repository.RetrieveSpecification.SpecOptions
import com.example.yurich.tuturutest.repository.local_storage.database.CitiesTable
import com.example.yurich.tuturutest.repository.local_storage.database.StationsTable
import com.example.yurich.tuturutest.repository.remote_storage.ResponseCity
import com.example.yurich.tuturutest.repository.remote_storage.ResponseStation
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteCreator
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType

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

    abstract fun getIdOfCity(): Long
}

@StorIOSQLiteType(table = CitiesTable.TABLE)
class StoragedCity(
        @StorIOSQLiteColumn(name = CitiesTable.COLUMN_COUNTRY)
        val countryTitle: String,
        @StorIOSQLiteColumn(name = CitiesTable.COLUMN_DISTRICT)
        val districtTitle: String?,
        @StorIOSQLiteColumn(name = CitiesTable.COLUMN_ID)
        val cityId: Long,
        @StorIOSQLiteColumn(name = CitiesTable.COLUMN_CITY)
        val cityTitle: String,
        @StorIOSQLiteColumn(name = CitiesTable.COLUMN_REGION)
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

    override fun getIdOfCity(): Long = cityId

    fun getAddress(): String {
        val builder = StringBuilder()

        appendField(countryTitle, builder)
        appendField(cityTitle, builder)

        return builder.toString().substring(2)
    }
}

@StorIOSQLiteType(table = StationsTable.TABLE)
class StoragedStation(
        @StorIOSQLiteColumn(name = StationsTable.COLUMN_COUNTRY)
        val countryTitle: String,
        @StorIOSQLiteColumn(name = StationsTable.COLUMN_DISTRICT)
        val districtTitle: String?,
        @StorIOSQLiteColumn(name = StationsTable.COLUMN_CITY_ID)
        val cityId: Long,
        @StorIOSQLiteColumn(name = StationsTable.COLUMN_CITY)
        val cityTitle: String,
        @StorIOSQLiteColumn(name = StationsTable.COLUMN_REGION)
        val regionTitle: String?,

        @StorIOSQLiteColumn(name = StationsTable.COLUMN_ID)
        val stationId: Long,
        @StorIOSQLiteColumn(name = StationsTable.COLUMN_STATION)
        val stationTitle: String,

        @StorIOSQLiteColumn(name = StationsTable.COLUMN_DIRECTION)
        val direction: Int = SpecOptions.DEPARTURE
) : StoragedEntity(){

    constructor(station: ResponseStation, direction: Int = SpecOptions.DEPARTURE) :
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

    override fun getIdOfCity(): Long = cityId

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