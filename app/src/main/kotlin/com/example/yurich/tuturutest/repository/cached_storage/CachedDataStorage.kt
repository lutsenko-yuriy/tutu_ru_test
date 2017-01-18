package com.example.yurich.tuturutest.repository.cached_storage

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.utils.DirectionConstants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 16.01.17.
 */
@Singleton
class CachedDataStorage @Inject constructor() {
    var cachedDepartureStations = mutableListOf<StoragedStation>()
    var cachedArrivalStations = mutableListOf<StoragedStation>()

    fun refreshCache(list: List<StoragedStation>) {
        cachedDepartureStations.clear()
        cachedArrivalStations.clear()

        cachedDepartureStations.addAll(list.filter { it.direction == DirectionConstants.DEPARTURE })
        cachedArrivalStations.addAll(list.filter { it.direction == DirectionConstants.ARRIVAL })
    }

    fun getDepartureStations(filter: String = ""): List<StoragedStation> {
        return cachedDepartureStations
                .filter {
                    it.stationTitle.contains(filter, true)
                }
    }

    fun getArrivalStations(filter: String = ""): List<StoragedStation> {
        return cachedArrivalStations
                .filter {
                    it.stationTitle.contains(filter, true)
                }
    }
}