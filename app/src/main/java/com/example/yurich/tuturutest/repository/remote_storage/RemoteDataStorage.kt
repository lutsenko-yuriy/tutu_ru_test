package com.example.yurich.tuturutest.repository.remote_storage

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.utils.DirectionConstants.ARRIVAL
import com.example.yurich.tuturutest.utils.DirectionConstants.DEPARTURE
import rx.Observable
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 16.12.16.
 */
@Singleton
class RemoteDataStorage @Inject constructor(val server: StationsApi) {

    public fun getStations(): Observable<List<StoragedStation>> {

        val observable = Observable.create<List<StoragedStation>> {
            subscriber ->

            val serverCall = server.getStationsList()
            val serverResponse = serverCall.execute()

            // Transform server response to storaged stations
            // if we received the response successfully
            if (serverResponse.isSuccessful) {
                val storagedStations = mutableListOf<StoragedStation>()
                storagedStations.addAll(getDepartureStations(serverResponse.body()))
                storagedStations.addAll(getArrivalStations(serverResponse.body()))

                subscriber.onNext(storagedStations)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(serverResponse.message()))
            }
        }

        return observable
                .subscribeOn(Schedulers.io())
    }

}


// Filters to get stations with specific direction
fun getDepartureStations(response: ServerResponse): List<StoragedStation> {
    val entitiesFromServer = response.citiesFrom
    return getEntities(entitiesFromServer, DEPARTURE)
}

fun getArrivalStations(response: ServerResponse): List<StoragedStation> {
    val entitiesFromServer = response.citiesTo
    return getEntities(entitiesFromServer, ARRIVAL)
}

fun getEntities(entitiesFromServer: List<ResponseCity>, direction: Int = DEPARTURE): MutableList<StoragedStation> {
    val storagedStations: MutableList<StoragedStation> = mutableListOf()

    for (city in entitiesFromServer) {
        city.stations!!.mapTo(storagedStations) {
            StoragedStation(it, direction)
        }
    }

    return storagedStations
}
