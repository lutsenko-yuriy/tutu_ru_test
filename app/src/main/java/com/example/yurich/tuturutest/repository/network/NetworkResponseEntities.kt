package com.example.yurich.tuturutest.repository.network

import android.text.TextUtils

/**
 * Some chanfes have been made in these classes
 * because we need only the part of data retrieved
 */
class ServerResponse(
        val citiesFrom: List<ResponseCity>,
        val citiesTo: List<ResponseCity>
)

class ResponseCity(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String?,
        val regionTitle: String?,

        val stations: List<ResponseStation>?
)

class ResponseStation(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String?,
        val regionTitle: String?,

        val stationId: Long,
        val stationTitle: String
)