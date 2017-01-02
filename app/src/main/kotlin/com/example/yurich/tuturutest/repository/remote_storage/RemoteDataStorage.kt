package com.example.yurich.tuturutest.repository.remote_storage

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import rx.Observable
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 16.12.16.
 */
@Singleton
class RemoteDataStorage @Inject constructor(val server: StationsApi) {

    fun getStations(): Observable<StoragedStation> {

        return server.getStationsList()
                .concatMap {
                    Observable.from(it.getStoragedStations())
                }
                .subscribeOn(Schedulers.io())
    }

}
