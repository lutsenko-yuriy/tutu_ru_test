package com.example.yurich.tuturutest.repository.remote_storage

import retrofit2.Call
import rx.Observable

/**
 * Created by yurich on 14.12.16.
 */
interface StationsApi {
    fun getStationsList(): Observable<ServerResponse>
}