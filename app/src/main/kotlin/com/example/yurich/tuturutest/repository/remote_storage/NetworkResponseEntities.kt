package com.example.yurich.tuturutest.repository.remote_storage

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.utils.DirectionConstants

/**
 * Some chanfes have been made in these classes
 * because we need only the part of data retrieved
 */
class ServerResponse(
        val citiesFrom: List<ResponseCity>,
        val citiesTo: List<ResponseCity>
) {
    // Filters to get stations with specific direction
    private fun getDepartureStations(): List<StoragedStation> {
        val entitiesFromServer = citiesFrom
        return getEntities(entitiesFromServer, DirectionConstants.DEPARTURE)
    }

    private fun getArrivalStations(): List<StoragedStation> {
        val entitiesFromServer = citiesTo
        return getEntities(entitiesFromServer, DirectionConstants.ARRIVAL)
    }

    private fun getEntities(entitiesFromServer: List<ResponseCity>, direction: Int): MutableList<StoragedStation> {
        val storagedStations: MutableList<StoragedStation> = mutableListOf()

        for (city in entitiesFromServer) {
            city.stations!!.mapTo(storagedStations) {
                StoragedStation(it, direction)
            }
        }

        return storagedStations
    }

    fun getStoragedStations(): List<StoragedStation> {
        return getDepartureStations() + getArrivalStations()
    }

}

class ResponseCity(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String,
        val regionTitle: String?,

        val stations: List<ResponseStation>?
)

class ResponseStation(
        val countryTitle: String,
        val districtTitle: String?,
        val cityId: Long,
        val cityTitle: String,
        val regionTitle: String?,

        val stationId: Long,
        val stationTitle: String
)