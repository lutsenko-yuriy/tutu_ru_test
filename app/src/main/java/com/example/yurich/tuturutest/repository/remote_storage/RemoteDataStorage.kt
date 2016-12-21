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

    fun getStations(): Observable<List<StoragedStation>> {

        val observable = server.getStationsList()
                .map(ServerResponse::getStoragedStations)

        return observable
                .subscribeOn(Schedulers.io())
    }

}
