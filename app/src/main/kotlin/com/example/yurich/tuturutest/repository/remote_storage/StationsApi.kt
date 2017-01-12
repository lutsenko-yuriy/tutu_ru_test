package com.example.yurich.tuturutest.repository.remote_storage

import rx.Observable

/**
 * Created by yurich on 14.12.16.
 */
interface StationsApi {
    fun getStationsList(): Observable<ServerResponse>
}