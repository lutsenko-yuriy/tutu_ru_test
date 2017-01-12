package com.example.yurich.tuturutest.repository.remote_storage

import rx.Observable
import javax.inject.Inject

/**
 * Created by yurich on 14.12.16.
 */
class TutuStationsApi @Inject constructor(val tutuServerApi: TutuServerApi) : StationsApi {
    override fun getStationsList(): Observable<ServerResponse> {
        return tutuServerApi.getStationsList()
    }
}