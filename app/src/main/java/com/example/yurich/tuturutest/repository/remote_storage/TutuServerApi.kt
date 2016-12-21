package com.example.yurich.tuturutest.repository.remote_storage

import retrofit2.Call
import retrofit2.http.GET
import rx.Observable

/**
 * Retrofit interface of server
 */
interface TutuServerApi {
    @GET("allStations.json")
    fun getStationsList(): Observable<ServerResponse>
}