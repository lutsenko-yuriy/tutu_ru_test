package com.example.yurich.tuturutest.repository.remote_storage

import com.example.yurich.tuturutest.repository.DataStorage
import com.example.yurich.tuturutest.repository.RetrieveSpecification.SpecOptions.ARRIVAL
import com.example.yurich.tuturutest.repository.RetrieveSpecification.SpecOptions.DEPARTURE
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 16.12.16.
 */
@Singleton
class RemoteDataStorage @Inject constructor(val server: StationsApi) {

    private fun getFilteredStations(filter: (ServerResponse) -> List<StoragedEntity>): Observable<List<StoragedEntity>> {
        val observable = Observable.create<ServerResponse> {
            subscriber ->

            val serverCall = server.getStationsList()
            val serverResponse = serverCall.execute()

            // Transform server response to storaged entities
            // if we received the response successfully
            if (serverResponse.isSuccessful) {
                subscriber.onNext(serverResponse.body())
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(serverResponse.message()))
            }
        }

        return observable.map(filter)

    }

    fun getStations(direction: Int): Observable<List<StoragedEntity>> {
        if (direction == DEPARTURE) {
            return getFilteredStations(::filterDepartureStations)
        } else if (direction == ARRIVAL) {
            return getFilteredStations(::filterArrivalStations)
        } else {
            throw IllegalArgumentException("It is either DEPARTURE or ARRIVAL! There is no third option!")
        }
    }

    fun putStations() {
        // You can't put data in RemoteDataStorage
        // right now
    }
}


// Filters to get stations with specific direction
fun filterDepartureStations(response: ServerResponse): List<StoragedEntity> {
    val entitiesFromServer = response.citiesFrom
    return getEntities(entitiesFromServer, DEPARTURE)
}

fun filterArrivalStations(response: ServerResponse): List<StoragedEntity> {
    val entitiesFromServer = response.citiesTo
    return getEntities(entitiesFromServer, ARRIVAL)
}

fun getEntities(entitiesFromServer: List<ResponseCity>, direction: Int = DEPARTURE): MutableList<StoragedEntity> {
    val storagedEntities: MutableList<StoragedEntity> = mutableListOf()

    for (city in entitiesFromServer) {
        storagedEntities.add(StoragedCity(city))

        city.stations!!.mapTo(storagedEntities) {
            StoragedStation(it, direction)
        }
    }

    return storagedEntities
}
