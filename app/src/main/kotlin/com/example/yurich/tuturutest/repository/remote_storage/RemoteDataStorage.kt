package com.example.yurich.tuturutest.repository.remote_storage

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 16.12.16.
 */
@Singleton
class RemoteDataStorage @Inject constructor(val server: StationsApi) {

    fun getStations(): Observable<List<StoragedStation>> {

        return server.getStationsList()
                .map(ServerResponse::getStoragedStations)
    }

}
