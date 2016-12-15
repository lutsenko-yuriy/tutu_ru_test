package com.example.yurich.tuturutest.network

import android.support.v4.text.TextUtilsCompat
import android.text.TextUtils

/**
 * Some restriction have been made in these classes
 * because we need only the part of data retrieved
 */
class ServerResponse(
        val citiesFrom: List<City>,
        val citiesTo: List<City>
)

class City(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Int,
        val cityTitle: String,
        val regionTitle: String?,

        val stations: List<Station>?
) {
    fun getAddress(): String {
        val builder = StringBuilder()

        appendField(countryTitle, builder)
        appendField(cityTitle, builder)

        return builder.toString().substring(2)
    }
}

class Station(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Int,
        val cityTitle: String?,
        val regionTitle: String?,

        val stationId: Int,
        val stationTitle: String
) {
    fun getAddress(): String {

        val builder = StringBuilder()

        appendField(districtTitle, builder)
        appendField(cityTitle, builder)
        appendField(regionTitle, builder)

        return builder.toString().substring(2)
    }

}

fun appendField(field: String?, builder: StringBuilder) {
    if (!TextUtils.isEmpty(field)) {
        builder
                .append(", ")
                .append(field)
    }
}